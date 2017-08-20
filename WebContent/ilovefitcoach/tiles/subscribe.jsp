<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%-- Using Struts2 Tags in JSP --%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<!--subscribe section start-->
<div class="subscribe-section">
    <div class="container">
        <div class="row">
            <div class="col-md-4 col-sm-5 col-xs-12">
                <div class="row">
                    <div class="ht-newsletter">
                        <h5>JOIN OUR NEWSLETTER</h5>
                        <h2>subscribe <b>newsletter</b></h2>
                    </div>
                </div>
            </div>
            <div class="col-md-8 col-sm-7 col-xs-12">
                <div class="row">
                    <form class="form-inline newsletter-forn-in">
                        <div class="col-md-9  col-sm-9 col-xs-12">
                            <div class="form-group">
                                <input class="form-control newsletter-input" type="text" placeholder="enter your email address">
                            </div>
                        </div>
                        <div class="col-md-3  col-sm-3 col-xs-12">
                            <div class="row text-center">
                                <button type="submit" class="subscribe-btn">subscribe<i class="fa fa-long-arrow-right" aria-hidden="true"></i></button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<!--subscribe section end-->