<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%-- Using Struts2 Tags in JSP --%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<div class="top-search">
    <form action="#">
        <span id="search_icon" class="search-icon"><i class="fa fa-search"></i></span>
        <button class="search-text">SEARCH</button>
        <input id="search_box" class="search-box" type="text" required="" placeholder="Search Here">
    </form>
</div>