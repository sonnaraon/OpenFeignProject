<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
<title>test</title>
<%@ include file="/WEB-INF/jspf/common-import.jspf" %>
 
<script type="text/javascript">

	$(document).ready(function() {
		
		_jqGrid();
		
		$("#btn_search").click(function() {
			jQuery("#list").jqGrid('setGridParam', {url: '/testsellist', datatype:'json', page:1, postData:{}}).trigger("reloadGrid");
		});
		
	});
	
	
	function _jqGrid() {
		$("#list").jqGrid({
//			url: '/testgriddata.do',
			width:1000,
			height:500,
			mtype: "GET",
			datatype: "json",
			colNames:['CORP_NM', 'GRAM_DIV_CD'],
			colModel:[
				{name:'f_name', index:'f_name', align:'center'},
				{name:'s_name', index:'s_name', align:'center'}
			         ],
			pager: '#pager',
			viewrecords: true,
			rowNum:10,
			rowList:[10,20,30],
			loadonce: true,
			postData:{},
			jsonReader: {
				root: "content>codeList",
				page: "content>page",
				total: "content>total",
				records: "content>codeListCnt",
				repeatitems: false,
				cell: "",
				id: "content>id"
			},
			loadComplete:function(jsondata) {
				var jdata = JSON.stringify(jsondata);
				alert(jdata);
			}
		})
		.jqGrid('navGrid', '#pager', {add:false, edit:false, del:false, search:false, refresh:false})
		.jqGrid('navButtonAdd', '#pager', {
			caption:"Columns",
			onClickButton: function () {
				$(this).jqGrid('columnChooser');
			}
		});
	}
	
</script>

</head>
<body>
<h2>DB 출력</h2>
<br/><br/>

<button id="btn_search" type="button">조회</button>

<br/><br/>

<table id="list"><tr><td></td></tr></table>
<div id="pager"></div>

</body>
</html>