;(function(){
    var _baseUrl  = "json/";
    var _get = function(url,param,success,error){
        _ajax('get',url,param,success,error);
    }
    var _post = function(url,param,success,error){
        _ajax('post',url,param,success,error);
    }
    var _ajax = function(type,url,param,mySuccess,myError){
        param.random = Math.random();
        url = _baseUrl+url;

        var options = {
            url : url,
            type : type||'get',
            data : param,
            timeout : 120000,//超时时间默认2分钟
            mySuccess : mySuccess,
            myError:myError,
            success : function(result){
                if(result.status == 'success'){
                    if(mySuccess)
                        mySuccess(result);
                }else{
                    App.showToast('\u9519\u8bef\uff1a'+ result.msg, 'error');//错误：
                    if(myError){
                        myError();
                    }
                }
            },
            cache : type == 'get'?false:true,
            error : function(xhr,type){
                _parseError(xhr,type,url);
                if(myError){
                    myError();
                }
            },
            dataType : 'json'
        }
        $.ajax(options);
    }

    function _parseError(xhr,type,url){
        var data = xhr.responseText,
            status = xhr.status;

        if(type == 'timeout'){
            App.showToast('\u8fde\u63a5\u670d\u52a1\u5668\u8d85\u65f6,\u8bf7\u68c0\u67e5\u7f51\u7edc\u662f\u5426\u7545\u901a\uff01','error'); //连接服务器超时,请检查网络是否畅通！
        }else if(type == 'parsererror'){
            if(data && data.indexOf('script_umlogin') != -1){
                App.showToast('\u4f60\u8fd8\u672a\u767b\u9646\uff0c\u8bf7\u8fdb\u884c\u767b\u9646\u3002','error');//你还未登陆，请进行登陆。
            } else {
                App.showToast('\u89e3\u6790\u8fd4\u56de\u7ed3\u679c\u5931\u8d25:' + status,'error');//解析返回结果失败:
            }
        }else if(type == 'error'){
            App.showToast('\u8fde\u63a5\u670d\u52a1\u5668\u5931\u8d25\uff1a'+status,'error');//连接服务器失败：
        }else{
            App.showToast('\u672a\u77e5\u5f02\u5e38\uff1a'+status,'error');//未知异常：
        }
    }

    window.MpassAPI = {
        'queryTaskList' : function(pageNo,success,error){
            _get('queryFileManagerTaskList.json',{pageNo:pageNo},success,error);
        },
        'getTaskInfo' : function(taskId,success,error){
            _get('getTaskInfo.json',{taskId:taskId},success,error);
        },
        'getAttachSealInfo' : function(taskId,success,error){
            _get('getTaskInfo.json',{taskId:taskId},success,error);
        },
        'getBargainInfo' : function(taskId,success,error){
            _get('getBargainInfo.json',{taskId:taskId},success,error);
        },
        'submitApply' : function(data,success,error){
            data.actionNo='111';
            _get('submitApply.json',data,success,error);
        },
        'returnApply2User' : function(data,success,error){
            data.actionNo='113';
            _get('jumpNextFlowStep.json',data,success,error);
        },
        'invalidApplication' : function(data,success,error){
            data.actionNo='108';
            _get('jumpNextFlowStep.json',data,success,error);
        },
        'getTaskFlow' : function(taskId,success,error){
            _get('getTaskFlow.json',{requestID:taskId},success,error);
        },
        'queryRequestChildDept' : function(data,success,error){
            _get('queryRequestChildDept.json',data,success,error);
        },
        'queryRequestParentDept' : function(data,success,error){
            _get('queryRequestParentDept.json',data,success,error);
        },
        'toRequestDept' : function(data,success,error){
            _get('updateApplicationAndJumpNext.json',data,success,error);
        },
        'queryUserList' : function(data,success,error){
            _get('queryUserList.json',data,success,error);
        },
        'submitApply2EOA' : function(data,success,error){
            _get('submitApply2EOA.json',data,success,error);
        },
        'coordinatorChangeDept' : function(data,success,error){
            _get('coordinatorChangeDept.json',data,success,error);
        },
        'getFilePageCount' : function(data,success,error){
            _get('getFilePageCount.json',data,success,error);
        }
    }
})();