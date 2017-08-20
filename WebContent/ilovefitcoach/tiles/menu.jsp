<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%-- Using Struts2 Tags in JSP --%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<div class="menu">
	<nav>
	    <ul>
	        <li><a href="index.html">HOME</a></li>
	        <li><a href="about-us.html">ABOUT US</a>
	            <ul class="singel-dropdown">
	                <li><a href="#">Dropdown Demo</a></li>
	                <li><a href="#">Dropdown Demo</a></li>
	                <li><a href="#">Dropdown Demo</a></li>
	                <li><a href="#">Dropdown Demo</a></li>
	                <li><a href="#">Dropdown Demo</a></li>
	                <li><a href="#">Dropdown Demo</a>
	                    <ul class="single-thirdlevel-menu">
	                        <li><a href="#">Thirdlevel Menu Demo</a></li>
	                        <li><a href="#">Thirdlevel Menu Demo</a></li>
	                        <li><a href="#">Thirdlevel Menu Demo</a></li>
	                        <li><a href="#">Thirdlevel Menu Demo</a></li>
	                        <li><a href="#">Thirdlevel Menu Demo</a></li>
	                    </ul>
	                </li>
	            </ul>
	        </li>
	        <li><a href="success-story.html">SUCCESS STORY</a></li>
	        <li><a href="event.html">EVENT</a></li>
	        <li><a href="blog.html">BLOG</a></li>
	        <li><a href="shop.html">SHOP</a></li>
	        <li><a href="contact.html">CONTACT</a>
	        </li>
	        <li><a href="index.html">PAGES</a>
	            <div class="single-megamenu">
	                <span>
	                    <a href="index.html">Home</a>
	                    <a href="about-us.html">About Us</a>
	                    <a href="blog.html">Blog</a>
	                    <a href="blog-details.html">Blog Details</a>
	                    <a href="event.html">Event</a>
	                    <a href="event-details.html">Event Details</a>
	                    <a href="pricing-table.html">Pricing Table</a>
	                </span>
	                <span>
	                    <a href="shop.html">Shop</a>
	                    <a href="product-single-page.html">Product Single</a>
	                    <a href="success-story.html">Success Story</a>
	                    <a href="success-story-details.html">Success Story Details</a>
	                    <a href="about-trainer.html">About Trainer</a>
	                    <a href="contact.html">Contact</a>
	                    <a href="my-account.html">My Account</a>
	                </span>
	                <span>
	                    <a href="exercise-plan.html">Exercise Plan</a>
	                    <a href="exercise-plan-details.html">Exercise Plan Details</a>
	                    <a href="forgot-password.html">Forgot Password</a>
	                    <a href="login.html">Login</a>
	                    <a href="registration.html">Registration</a>
	                </span>
	                </div>
	            </li>
	        </ul>
	    </nav>
	</div>