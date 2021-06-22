<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>

	<%@ include file="../components/header.jsp" %>
		<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

			<!-- Breadcrumb Start -->
			<div class="breadcrumb-wrap">
				<div class="container-fluid">
					<ul class="breadcrumb">
						<li class="breadcrumb-item"><a href="#">Home</a></li>
						<li class="breadcrumb-item"><a href="#">Products</a></li>
						<li class="breadcrumb-item active">My Account</li>
					</ul>
				</div>
			</div>
			<!-- Breadcrumb End -->

			<!-- My Account Start -->
			<div class="my-account">
				<div class="container-fluid">
					<div class="row">
						<div class="col-md-3">
							<%@ include file="./components/sidebar.jsp" %>
						</div>
						<div class="col-md-9">
							<div class="tab-content">
								<div >
									<h4>Dashboard</h4>
									<p>
										Lorem ipsum dolor sit amet, consectetur adipiscing elit. In condimentum quam ac mi viverra dictum. In efficitur ipsum diam, at dignissim lorem tempor in. Vivamus tempor hendrerit finibus. Nulla tristique viverra nisl, sit amet bibendum ante suscipit non. Praesent in faucibus tellus, sed gravida lacus. Vivamus eu diam eros. Aliquam et sapien eget arcu rhoncus scelerisque.
									</p> 
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- My Account End -->

			<%@ include file="../components/footer.jsp" %>