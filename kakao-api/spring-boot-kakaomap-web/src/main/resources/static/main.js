$('#dynamicTBody').empty();
temp = location.href.split("?")
data = temp[1].split("=")
apiKey = data[1]
history.replaceState({}, null, location.pathname);
searchQuery = $('#search').val();
apiurl = null;
page = 1;
listCnt = 0;
additionalFlag = false;
$(document).ready(function(){
    popularRequest();
    rankAnimate();
})
startPopularRank()
// alert(apiKey)

$(document).ready(function() {
    $('#find-btn').eq(0).bind("click", function() {
        searchQuery = $('#search').val();
        if(searchQuery == ''){
            alert('키워드를 입력해주세요');
            return;
        }
        apiurl = 'http://localhost:8080/v1/map/search/keyword?query=' + searchQuery;

        $.ajax({
            method : 'GET',
            contentType :'application/json',
            url : 'http://localhost:8080/v1/map/search/keyword?query=' + searchQuery,
            beforeSend : function (xhr){
                xhr.setRequestHeader("Authorization", apiKey)
            },

            success : function(data) {
                if (data.message == '성공') {
                    if(data.meta.total_count <= 15) {
                        additionalFlag = false;
                    }else{
                        additionalFlag = true;
                    }
                    page = 1;
                    listCnt = 0;
                    $('#dynamicTBody').empty();

                    if(data.meta.total_count > 500){
                        alert("검색된 결과 수 : " + data.meta.total_count  + "\n결과 데이터는 45건 이상은 상세조회되지않습니다. \n상세한 키워드를 입력해보는건 어떨까요?");
                    }
                    setTableData(data.documents);
                    setMetaData(data.meta, data.documents.length);
                    $("#aside").animate({left:0},2000, function(){
                        window.location = "#map_table_id";
                    });
                } else {
                    alert('데이터 요청에 실패하였습니다.');
                }
            },
            error : function(jqXHR, exception) {
                if(jqXHR.status == 404){
                    alert('조회된 데이터가 없습니다');
                }else {
                    alert('서버 연결 도중 에러가 발생하였습니다. 다시 시도해 주십시오.');
                }
            }
        });
    })
});

//화면 제일 하단 스크롤시 추가 data 얻어옴
window.onscroll = function() {
    if ((window.innerHeight + window.scrollY) >= document.body.offsetHeight) {
        if (additionalFlag) {
            page++;
            additionalList(page)
        }
    }
};

function additionalList(page){
    $.ajax({
        method : 'GET',
        contentType :'application/json',
        url : 'http://localhost:8080/v1/map/search/keyword?query=' + searchQuery + '&page=' + page,
        beforeSend : function (xhr){
            xhr.setRequestHeader("Authorization", apiKey)
        },

        success : function(data) {
            // alert(JSON.stringify(data));
            if (data.message == '성공') {
                setTableData(data.documents);
                if(data.meta.is_end == true)
                    additionalFlag = false;
            } else {
                alert('데이터 요청에 실패하였습니다.');
            }
        },
        error : function(e) {
            alert('서버 연결 도중 에러가 발생하였습니다. 다시 시도해 주십시오.');
        }
    });
}
// 검색 테이블 setter
function setTableData(obj){
    var html = "";
    // $('#dynamicTBody').empty();
    var inputId="";
    for(key in obj){
        listCnt ++;
        html += '<tr><td>' + listCnt + '</td>'
        html += '<td>' + obj[key].place_name + '</td>'
        html += '<td>' + obj[key].category_name + '</td>'
        html += '<td>' + obj[key].road_address_name + '</td>'
        // console.log("obj[key].detailUri:" ,obj[key].detailUri)
        html += '<td>';
        // html += '<input type="button" id="detail_link_id" onclick="detailFunction("'+url+'");" class="view" title="View" data-toggle="tooltip"><i class="material-icons">&#xE417;</i></input>'

        //obj[key].place_name
        inputId = "detail_link_id" + listCnt;
        html += '<input type="button" value="더보기" id='+ inputId +' data-url='+ obj[key].detailUri +' class="view url_input" title="View" data-toggle="tooltip"></input>'
        html += '</td></tr>';
    }
    $('#dynamicTBody').append(html);

    document.querySelectorAll(".url_input").forEach(function (e){
       e.addEventListener("click", detailFunction);
    });
}

function detailFunction(evt){
    target = evt.target;
    target.dataset.toggle="modal";
    target.dataset.target="#myModal";
    //ajax url
    url = target.dataset.url;
    $.ajax({
        method : 'GET',
        contentType :'application/json',
        url : url,
        beforeSend : function (xhr){
            xhr.setRequestHeader("Authorization", apiKey)
        },

        success : function(data) {
            // alert(JSON.stringify(data));
            if (data.message == '성공') {
                document.querySelector("#Modal-head").innerText = data.place_name;

                document.querySelector("#category_name").innerText = data.category_name;
                document.querySelector("#category_group_code").innerText = data.category_group_code;
                document.querySelector("#category_group_name").innerText = data.category_group_name;
                document.querySelector("#phone").innerText = data.phone;
                document.querySelector("#address_name").innerText = data.address_name;
                document.querySelector("#road_address_name").innerText = data.road_address_name;
                document.querySelector("#place_url").href = data.place_url;
                document.querySelector("#place_url").innerText = '클릭';
            } else {
                alert('데이터 요청에 실패하였습니다.');
            }
        },
        error : function(e) {
            alert('서버 연결 도중 에러가 발생하였습니다. 다시 시도해 주십시오.');
        }
    });
}
// meta data
function setMetaData(obj, obj2){
    $('#total_cnt_id').html(obj.total_count);
    $('#page_entries_cnt').html(obj2);
}

//enter key 막기
$(document).keypress(function(e) {
    if (e.keyCode == 13) e.preventDefault();
});

//rank
// refreshFlag = false;
function rankAnimate() {
    var count = $('#rank-list li').length;
    var height = $('#rank-list li').height();

    for(var i = 0; i < count ; i++){
        $('#rank-list ol').delay(2000).animate({
            top: -height * i,
        }, 500)
    }
}

    function startPopularRank(){
        setInterval(popularRequest, 10000)
    }

    function popularRequest() {
        $.ajax({
            method: 'GET',
            contentType: 'application/json',
            url: "http://localhost:8080/v1/map/popular",
            async: true,
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Authorization", apiKey)
            },
            success: function (result) {
                rankRefresh(result.popularKeywords)
            }
        });
    }

    function rankRefresh(result){
        var html = "";
        $('#rank-list-id').empty();
        for(var i = 0; i < result.length; i++){
            html += '<li>' + parseInt(i+1) + '.  [' + result[i].keyword + '] 검색수: ' + result[i].hitCnt + '</li>'
        }
        $('#rank-list-id').append(html);
        rankAnimate()
    }
