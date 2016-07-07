<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Banking Homepage</title>
<%-- <link href="<s:url value="/resources" />/css/spitter.css" rel="stylesheet" type="text/css"/> --%>

<%-- 	<link rel="stylesheet" href="<c:url value="/resources"/>/css/spitter.css" type="text/css"/> --%>
<%-- <link rel="stylesheet" href="<c:url value="/resources"/>/css/style.css" type="text/css" /> --%>
<%-- <link href="<s:url value="/resources" />/css/style.css" rel="stylesheet" type="text/css"/> --%>

<%-- <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/WEB-INF/css/spitter.css"/> --%>
<style type="text/css">

body {
	background-color: #f4f4f4;
	color: #333;
	font-family: Arial, Helvetica, sans-serif;
	font-size: .8em;
}
#header {
	/* color: #1947A3; */
	margin-bottom: 7px;
	width: 100%;
	height: 60px;
	background-color:#0278C2;
 
}

#top-header {
	/* color: #1947A3; */
	margin-bottom: 1px;
	width: 100%;
	height: 20px;
	background-color: #00395D; 
}

#footer {
	margin-bottom: 1px;
	width: 1024px;
	height: 20px;
	background-color: #00395D;
	
	position:absolute;
	bottom:0;
}

/* Application title to be used in Header */
.application-title { 
	/* background-color: #ebebeb; */
	font-weight: bold;
	padding: 20px;
	font-size: 1.2em;
	color: white;
	
}

.page-title { 
	background-color: #ebebeb;
	font-weight: bold;
	padding: 10px;
	font-size: 1.0em;
	color: #333;
	text-align: left;
}

.logout-icon {
	margin-left: 4px;
	width: 17px;
	height:17px; 
	border: 1px solid white;
}
#navigation {
	background: url("/basman/images/red_bar_bg.png") repeat-x;
	/*width: 1026px;*/
	width: 100%;
	height: 26px;
	margin-bottom: 8px;
}
#container {
	margin: 0 auto;
	width: 1024px;	
	/* min-height: 100%;
	margin-bottom: 100px; */
	/*width:	95%	*/
}

input[type=text], input[type=password], textarea {
	background-color: white;
	font-size: .9em;
	margin: 0px;
	margin-right: 5px;
	border: 1px solid #dcdcdc;
	padding: 3px;
	color: #656565;
}
.textarea {
	resize: none;
	font-family: Arial, Helvetica, sans-serif; /* this fixes the firefox visualization problem */  
}
a:link, a:visited, a:active {
	text-decoration: none;
	/* color: #333; */
	color: blue;
}
a:hover {
	color: red;
}

#top-anchor {
/* 	text-decoration: none; */
	/* color: #333; */
	color: white;
	/* padding: 3px; */
	font-size: 0.8em;
}


.image-link	{
	border	: 0px;
}

.app-name {
	color: #656565;
	line-height: .4em;
	font-size: 1.8em;
	padding-bottom: 5px;
}
h1, h2, h3 {
	margin: 0px;
}
h3 {
	font-weight: normal;
	font-size: 1em;
	font-weight: bold;
}
/*button light */
button, input[type=submit], input[type=reset] {
	-moz-box-shadow:0px 0px 3px rgba(0,0,0,0.2);
	-webkit-box-shadow:0px 0px 3px rgba(0,0,0,0.2);
 	margin-top: 10px;
 	margin-bottom: 5px;
	margin-right: 2px;
 
	color:#656565;
	font-weight: bold;
	border:1px solid #d5d5d5;
	
	background-image: -webkit-gradient( linear, left bottom, left top, color-stop(0.10, rgb(233,233,233)), color-stop(0.90, rgb(255,255,255)) );
	background-image: -moz-linear-gradient( center bottom, rgb(233,233,233) 10%, rgb(255,255,255) 90% );
 	font-size: .9em;
	padding:5px 5px 5px 5px;
}
button:hover, input[type=submit]:hover, input[type=reset]:hover {
	background:rgba(240,240,240,1);
}
button:active, input[type=submit]:active, button:focus, input[type=submit]:focus, input[type=reset]:focus {
	background:-webkit-gradient(linear,0% 100%,0% 0%,from(rgba(255,255,255,1)),to(rgba(185,185,185,1)));
	background:-moz-linear-gradient(bottom,rgba(255,255,255,1),rgba(185,185,185,1));
}
button:disabled {
	color:rgba(0,0,0,0.4);
	text-shadow:1px 1px 0px rgba(255,255,255,0.5);
	background:rgba(220,220,220,1);
}
.no-margin {
	margin: 0px !important;
}
.odd {
	background-color: rgb(245,245,245);
}
.locked {
	font-style: italic;
}
.deleted {
	text-decoration: line-through;
}
.tokenExpired {
	font-style: italic;
}
.tokenNotUsed {
	color: red;
}
.tooltip {
/*	font-size: 12px;*/
	background-color: gray;
	padding: 2px 3px;
	color: rgb(245,245,245);
	-moz-border-radius:4px;
	-webkit-border-radius:4px;
	border-radius:4px;
}

.activeMenuItem {
	color: red !important;
}
.h1 {
	color: red;
	font-weight: bold;
	font-size: 2.5em;
}
.h2 {
	font-family: Verdana;
	color: #777;
	font-size: 1.8em;
}
#content-container {
	color: #656565;
	float: left;
	/*width: 1026px;	*/	
	width:	100%;
}

.generic-div {
	padding: 3px;
}

.generic-label {
	padding: 3px;
}

/* ==================================================================== LEFT NAVIGATION MENU SYTLES ==================================================================== */

#menu-left {
	background-color: white;
	border: 1px solid white;
	clear: left;
	float: left;
	width: 140px;
	margin: 0px;
	display: inline;
	-moz-box-shadow: 2px 2px #d8d8d8;
	-webkit-box-shadow: 2px 2px #d8d8d8;
	box-shadow: 2px 2px #d8d8d8;
	min-height: 100px;
}
#menu-left, ul {
	margin: 0px;
	padding: 0px;
}
#menu-left, ul, li {
	list-style-type: none;
	display: inline;
}
#menu-left ul li > ul > li > a:before {
	content: "\00BB \0020";
}
#menu-left ul li > div { /*menu category title*/
	background-color: #ebebeb;
	font-weight: bold;
	display: block;
	padding: 7px;
	color: #333;
	font-size: 1.0em;
}
#menu-left ul li > div em { /* disabled element */
	color: gray !important;
}
#menu-left ul li:not(:first-of-type) > div { /*menu category title top border*/
	border-top: 1px solid white;
}
#menu-left ul li > ul > li > a { /*menu items*/
	background-color: white;
	display: block;
	padding: 9px;
	color: #656565;
}
#menu-left ul li > ul > li:not(:last-of-type) > a { /*menu items*/
	border-bottom: 1px solid #ebebeb;
}
#menu-left ul li > ul > li a:hover {
	color: red;
}

.margin-right {
	text-align: center;
	padding-right: 5px;
}
#content h2 {
	margin: 0px;
}
#content {
	background-color: white;
	border: 1px solid white;
	display: inline;
	-moz-box-shadow: 2px 2px #d8d8d8;
	-webkit-box-shadow: 2px 2px #d8d8d8;
	box-shadow: 2px 2px #d8d8d8;
		float: left;
    margin-bottom: 10px;
    margin-left: 20px;
	width: 860px;	
	/*width: float;	*/
}
.content-data {
	padding: 5px;
}
#content h3 {
	margin: 0px;
}

/* ==================================================================== TABLE SYTLES ==================================================================== */

table.dataview {
	border: 1px solid #dcdcdc;
	width: 100%;
	font-size: .9em;
}
table.dataview, thead, th {
	/* background-color: #525252; */
	background-color: #0278C2;
	padding: 6px;
	color: white;
	text-align: left;
	white-space: nowrap;
}
 table.dataview, thead th:not(:last-of-type)  {
	border-right: 1px solid #696969; 
}  
table.dataview, td  {
	padding: 5px;
	vertical-align: top;
	background-color: white;
	padding: 6px;
	color: black;
	text-align: left;
	white-space: nowrap;
	
}
table.dataview, tbody tr:not(:last-of-type) td  {
	border-bottom: 1px dotted #dcdcdc;
}
table.dataview, tbody td:not(:last-of-type)  {
	border-right: 1px solid #dcdcdc;
}
table.editpage, td {
	padding: 1px;
}

table.application-list {
	padding: 2px;
}
table.application-list-fields {
	color: black;
}

td.border {
	border: 1px solid #dcdcdc;
}
table.role {
	width: 100%;
}
table.icon-panel td a span.content {
	white-space: nowrap;
}

.input {
	width: 250px;
}
button, input[type=submit], input[type=reset] {
 	font-size: .8em;
}
.info-panel {
	background-color: #ebebeb;
}
.info-panel, ul {
	padding-top: 5px;
	padding-bottom: 5px;
}
.view-container {
	border: 0px;
	border-top: 1px solid #dcdcdc;
	margin-top: 5px;
}
.view-container-no-text {
	border: 1px solid #dcdcdc;
	margin-top: 0px;
}
.view-container legend, .view-container-no-text legend {
	color: #656565;
	font-size: 1.1em;
	font-weight: bold;
}

div.application-list-fields {
	padding: 1px;
	border: 1px solid #dcdcdc;
	width: 250px;
	max-height: 77px;
	min-height: 18px;
	overflow: auto;
}
.application-list td {
	padding: 2px;
}
.table-link {
	white-space: nowrap;
	border-right: 0px !important;
	font-weight: bold;
	font-style: normal;
}

input[disabled="disabled"], input.disabled, textarea[disabled="disabled"], textarea.disabled {
	color: black;
	background-color: white;
}
.container {
	padding: 5px 0px;
	background-color: #ebebeb;
}
/* tree component */
div.tree {
	overflow: auto;
}

.all-width {
	width: 100%;
}
.appPropsList {
	background-color: #ebebeb;
	border-bottom: 1px solid #dcdcdc;
	border-right: 1px solid #dcdcdc;
	padding: 1px;
	font-weight: bold; 
	margin-top: 5px;
}
ul.list {
	margin: 0px;
	padding: 2px;
}
ul.list li {
	border-bottom: 1px dashed #dcdcdc;
	margin: 0px;
	padding: 2px;
	list-style-type: none;
}
.padding-div {
	padding: 10px;
}

.feedbackPanelERROR { color: red }

</style>
</head>
<body>
<s:url var="logoutUrl" value="/static/j_spring_security_logout" ></s:url>
<s:url var="logoutUrl" value="/contact" ></s:url>


	<div id="container" align="left">
		<div id="top-header">
		<a id="top-anchor" href="<c:url value="/"/> ">Home |</a>
		<a id="top-anchor" href='<c:url value="${logoutUrl}"/>'>Contact</a>
		</div>
		<!-- Insert links to register, logout, contact -->
		<div id="header">
			<tiles:insertAttribute name="header"></tiles:insertAttribute>
		</div>
		<div id="content-container">
			<div id="menu-left">
				<tiles:insertAttribute name="menu-left" />
			</div>

			<div id="content" align="center">
				<tiles:insertAttribute name="content" />
			</div>

		</div>
		<!--End content-container  -->

		<div id="footer"></div>

	</div>

</body>
</html>	