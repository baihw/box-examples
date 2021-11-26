/**
 * examples
 *
 * $(function () {
 *   $.box({ baseApi: '127.0.0.1:9000/api/' });
 * });
 **/
(function ($) {
    //默认参数
    const _def_box_options = {
        baseApi: 'http://127.0.0.1:9000/api/'
    };

    //私有方法，检测参数是否合法
    function _isValid(_options) {
        // return !_options || (_options && typeof _options === "object") ? true : false;
        if (!_options) return true;
        if (typeof _options === "object") return true;
        return false;
    }

    $.extend({
        "box": function (_options) {
            //检测用户传进来的参数是否合法
            if (!_isValid(_options)) {
                console.error("invalid options:", _options);
                return this;
            }
            // 使用jQuery.extend 覆盖插件默认参数
            let _opts = $.extend({}, _def_box_options, _options);
            console.trace("this:", this);
            console.trace("_opts:", _opts);

            function _sendRequest(url, method, params, callback) {
                console.trace("url:", url);
                console.trace("method:", method);
                console.trace("params:", params, ", type: ", (typeof params));

                let _url = 0 == url.indexOf("http") ? url : _opts.baseApi + url;
                let _method = method ? method : 'GET';
                let _params = '';
                if (params) {
                    if (params instanceof FormData) {
                        _params = params;
                    } else if (params instanceof Object) {
                        let _paramsArr = [];
                        for (let _name in params) {
                            _paramsArr.push(_name + '=' + encodeURIComponent(params[_name]));
                        }
                        _params = _paramsArr.join('&');
                    }
                }
                let _xhr = new XMLHttpRequest();
                _xhr.open(_method, _url, true);
                _xhr.onreadystatechange = function () {
                    console.info("status:", _xhr.status, ", readyState:", _xhr.readyState, ", responseText:", _xhr.responseText);
                    if (4 == _xhr.readyState) {
                        callback && callback(_xhr.responseText);
                    }
                };
                // _xhr.onload = function () {
                //     console.info("status:", _xhr.status, ", readyState:", _xhr.readyState, ", responseText:", _xhr.responseText);
                // }
                console.debug("_url:", _url);
                console.debug("_method:", _method);
                console.debug("_params:", _params);
                if (_params.length) {
                    _xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                }
                _xhr.send(_params);
            }

            function _ajaxGet(url, params, callback) {
                return _sendRequest(url, 'GET', params, callback);
            }

            function _ajaxPost(url, params, callback) {
                return _sendRequest(url, 'POST', params, callback);
            }

            function _ajaxGetJson(url, params, callback) {
                return _sendRequest(url, 'POST', params, (_data) => {
                    _defResponseHandle(_data, callback);
                })
            }

            // 默认的响应处理逻辑
            function _defResponseHandle(responseText, callback) {
                if (!callback) {
                    console.warn("ignore data:", responseText);
                    return;
                }
                try {
                    const _jsonData = JSON.parse(responseText);
                    if (200 == _jsonData.code) {
                        callback(_jsonData);
                    } else if (_jsonData.message && -1 != _jsonData.message.indexOf('先登陆')) {
                        window.location.href = "/login.html";
                    } else {

                        window.location.href = '/error/error.html?data=' + encodeURI(responseText);
                    }
                } catch (_e) {
                    console.warn("fail convert json:", responseText);
                    callback(_data);
                }
            }

            function _getUrlParam(name) {
                let _nameReg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
                let _nameVal = window.location.search.substr(1).match(_nameReg);  //匹配目标参数
                if (null == _nameVal) return null;
                console.trace("_nameVal:", _nameVal);
                let _result = decodeURI(_nameVal[2]);
                console.trace("_result:", _result);
                // var _slashReg = new RegExp("%22", "g");
                // _result = _result.replace(_slashReg, "\"");
                return _result;
            }

            function _toString() {
                return 'box{ "options": ' + JSON.stringify(_opts) + ' }';
            }

            return {
                get: _ajaxGet,
                post: _ajaxPost,
                getJson: _ajaxGetJson,
                getUrlParam: _getUrlParam,
                toString: _toString
            };
        }
    });

    /**
     * 测试实例插件
     * $.fn.hello.format = function (txt) { return "<em>" + txt + "</em>" }
     * $(function () { $("testDiv1").hello(); } );
     */
    $.fn.extend({
        "hello": function () {
            console.log("this:", this, ", $this:", $(this));
            let _thisHtml = this.html();
            console.log("_thisHtml:", _thisHtml);
            let _newHtml = $.fn.hello.format(_thisHtml)
            this.html(_newHtml);
            console.log("_newHtml:", _newHtml);
            return this;
        }
    });
    //公共的格式化 方法. 默认是加粗，用户可以通过覆盖该方法达到不同的格式化效果。
    $.fn.hello.format = function (str) {
        return "<strong>" + str + "</strong>";
    }
})(window.jQuery);