<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="enter" %>
<jsp:useBean id="productFilter" scope="session" type="entity.ProductFilter"/>

<body>
	<div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
		<ol class="carousel-indicators">
			<li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
			<li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
			<li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
			<li data-target="#carouselExampleIndicators" data-slide-to="3"></li>
		</ol>
		<div class="carousel-inner">
			<div class="carousel-item item1 active">
				<div class="container">
					<div class="w3l-space-banner">
						<div class="carousel-caption p-lg-5 p-sm-4 p-3">
							<p>Get flat
								<span>10%</span> Cashback</p>
							<h3 class="font-weight-bold pt-2 pb-lg-5 pb-4">The
								<span>Big</span>
								Sale
							</h3>
							<a class="button2" href="product.html">Shop Now </a>
						</div>
					</div>
				</div>
			</div>
			<div class="carousel-item item2">
				<div class="container">
					<div class="w3l-space-banner">
						<div class="carousel-caption p-lg-5 p-sm-4 p-3">
							<p>advanced
								<span>Wireless</span> earbuds</p>
							<h3 class="font-weight-bold pt-2 pb-lg-5 pb-4">Best
								<span>Headphone</span>
							</h3>
							<a class="button2" href="product.html">Shop Now </a>
						</div>
					</div>
				</div>
			</div>
			<div class="carousel-item item3">
				<div class="container">
					<div class="w3l-space-banner">
						<div class="carousel-caption p-lg-5 p-sm-4 p-3">
							<p>Get flat
								<span>10%</span> Cashback</p>
							<h3 class="font-weight-bold pt-2 pb-lg-5 pb-4">New
								<span>Standard</span>
							</h3>
							<a class="button2" href="product.html">Shop Now </a>
						</div>
					</div>
				</div>
			</div>
			<div class="carousel-item item4">
				<div class="container">
					<div class="w3l-space-banner">
						<div class="carousel-caption p-lg-5 p-sm-4 p-3">
							<p>Get Now
								<span>40%</span> Discount</p>
							<h3 class="font-weight-bold pt-2 pb-lg-5 pb-4">Today
								<span>Discount</span>
							</h3>
							<a class="button2" href="product.html">Shop Now </a>
						</div>
					</div>
				</div>
			</div>
		</div>
		<a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
			<span class="carousel-control-prev-icon" aria-hidden="true"></span>
			<span class="sr-only">Previous</span>
		</a>
		<a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
			<span class="carousel-control-next-icon" aria-hidden="true"></span>
			<span class="sr-only">Next</span>
		</a>
	</div>
	<div class="ads-grid py-sm-5 py-4">
    		<div class="container py-xl-4 py-lg-2">
    			<h3 class="tittle-w3l text-center mb-lg-5 mb-sm-4 mb-3">
    				<span>O</span>ur
    				<span>N</span>ew
    				<span>P</span>roducts</h3>
    			<div class="row">
    				<div class="agileinfo-ads-display col-lg-9">
    					<div class="wrapper">
    						<div class="product-sec1 px-sm-4 px-3 py-sm-5  py-3 mb-4">
    							<div class="row" >

    						    <c:if test="${empty products}">
    						        <div class="product-sec1 px-sm-4 px-3 py-sm-5  py-3 mb-4" style="margin: 0 auto;">
                                        <h3 class="heading-tittle text-center font-italic">There are no suitable products</h3>
                                    </div>
    						    </c:if>

                                <c:forEach items="${products}" var="product">
    								<div class="col-md-4 product-men mt-5">
    									<div class="men-pro-item simpleCart_shelfItem">
    										<div class="men-thumb-item text-center">
    											<img src="${pageContext.request.contextPath}/${product.image}" alt="">
    											<div class="men-cart-pro">
    												<div class="inner-men-cart-pro">
    													<a href="single.html" class="link-product-add-cart" >Quick View</a>
    												</div>
    											</div>
    										</div>
    										<div class="item-info-product text-center border-top mt-4">
    											<h4 class="pt-1">
    												<a href="single.html">${product.name}</a>
    											</h4>
    											<div class="info-product-price my-2">
    												<span class="item_price">${product.price}$</span>
    											</div>
    											<div class="snipcart-details top_brand_home_details item_add single-item hvr-outline-out">
    												<form action="#" method="post">
    													<fieldset>
    														<input type="hidden" name="cmd" value="_cart" />
    														<input type="hidden" name="add" value="1" />
    														<input type="hidden" name="business" value=" " />
    														<input type="hidden" name="item_picture" value="${pageContext.request.contextPath}${product.image}">
    														<input type="hidden" name="item_name" value=${product.name} />
    														<input type="hidden" name="item_number" value=${product.id} />
    														<input type="hidden" name="amount" value=${product.price} />
    														<input type="hidden" name="currency_code" value="USD" />
    														<input type="hidden" name="return" value=" " />
    														<input type="hidden" name="cancel_return" value=" " />
    														<input type="submit" name="submit" value="Add to cart" class="button btn" />
    													</fieldset>
    												</form>
    											</div>
    										</div>
    									</div>
    								</div>
                                </c:forEach>

    							</div>
    						</div>
    						<enter:pagination pagination="${pagination }"/>
    					</div>
    				</div>

    				<div class="col-lg-3 mt-lg-0 mt-4 p-lg-0">
                        <div class="side-bar p-sm-4 p-3">
                            <div class="search-hotel border-bottom py-2">
                                <h3 class="agileits-sear-head mb-3">Filter</h3>
                                <form action="${pageContext.request.contextPath}/main" method="get">
                                    <input type="search" placeholder="Product name..." name="nameFilter" >
                                    <input type="search" placeholder="Limit of products..." name="productLimit" >

                                    <ul class="cute">
                                        <c:forEach items="${manufacturers}" var="manufacturer">
                                            <li class="subitem1">
                                                <input type="checkbox" name="manufacturer-checkbox" value="${manufacturer.id}"
                                                <c:if test="${  fn:contains( productFilter.manufacturers, manufacturer.id ) }">checked
                                                </c:if>
                                                />
                                                ${manufacturer.name}
                                            </li>
                                        </c:forEach>
                                    </ul>

                                     <c:forEach items="${categories}" var="category">
                                         <li class="subitem1">
                                             <input type="checkbox" name="category-checkbox" value="${category.id}"
                                                <c:if test="${  fn:contains( productFilter.categories, category.id ) }">
                                                    checked
                                                </c:if>
                                             >
                                             ${category.name}
                                         </li>
                                     </c:forEach>


                                    <b> Price: </b></br>

                                    <output name="priceFromOutputName" id="priceFromOutputId">${filter.minPrice}</output>
                                    <input type="range" name="minPrice" id="priceFromInputId" value="${filter.minPrice}" min="0"
                                           max="10000"
                                           oninput="priceFromOutputId.value = priceFromInputId.value"><br>


                                    <output name="priceToOutputName" id="priceToOutputId">${filter.maxPrice}</output>
                                    <input type="range" name="maxPrice" id="priceToInputId" value="${filter.maxPrice}" min="0"
                                           max="10000"
                                           oninput="priceToOutputId.value = priceToInputId.value"></br>

                                    <div class="range border-bottom py-2">
                                        <h3 class="agileits-sear-head mb-3">Sort</h3>
                                        <div class="w3l-range">

                                            <p><input name="sort" type="radio" value="name">Name</p>
                                            <p><input name="sort" type="radio" value="price" checked>Price</p>

                                    </div>

                                    <div class="range border-bottom py-2">
                                        <h3 class="agileits-sear-head mb-3">Sort kind</h3>
                                        <div class="w3l-range">

                                            <p><input name="sortKind" type="radio" value="increase">Increase</p>
                                            <p><input name="sortKind" type="radio" value="decrease" checked>Decrease</p>

                                            <input type="submit" name="submit" value="" class="button btn" />
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
    			</div>
    		</div>
    	</div>
    </div>

