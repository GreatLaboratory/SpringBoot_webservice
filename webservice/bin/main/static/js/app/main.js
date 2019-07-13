//var main = {
//    init : function () {
//        var _this = this;
//        $('#btn-save').on('click', function () {
//            _this.save();
//        });
//    },
//    save : function () {
//        var data = {
//            title: $('#title').val(),
//            author: $('#author').val(),
//            content: $('#content').val()
//        };
//
//        $.ajax({
//            type: 'POST',
//            url: '/posts',
//            dataType: 'json',
//            contentType:'application/json; charset=utf-8',
//            data: JSON.stringify(data)
//        }).done(function() {
//            alert('글이 등록되었습니다.');
//            location.reload();
//        }).fail(function (error) {
//            alert(error);
//        });
//    }
//
//};
//
//main.init();

var main= {
		init: function() {
			$("#btn-save").on("click", function(){
				/* 여기 */
//				$("#method").val("put");
				// http 메소드방식을 여기서 지정 _method라는 애가 이걸 가능하게 해줌 -> HiddenHttpMethodFilter
				
				var frm = $("#frm")[0];
				frm.action = "/posts/"
				frm.submit();
			});
		}
}

main.init();
