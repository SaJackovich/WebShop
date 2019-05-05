<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<jsp:useBean id="products" scope="request" type="java.util.List"/>

	<div class="privacy py-sm-5 py-4">
		<div class="container py-xl-4 py-lg-2">
			<!-- tittle heading -->
			<h3 class="tittle-w3l text-center mb-lg-5 mb-sm-4 mb-3">
				<span>C</span>heckout
			</h3>
			<!-- //tittle heading -->
			<div class="checkout-right">
				<h4 class="mb-sm-4 mb-3">Your shopping cart contains:
					<span>3 Products</span>
				</h4>
				<div class="table-responsive">
				<form action="${pageContext.request.contextPath}/order" method="post" id="form1">

					<table class="timetable_sub">
						<thead>
							<tr>
								<th>SL No.</th>
								<th>Product</th>
								<th>Quality</th>
								<th>Product Name</th>

								<th>Price</th>
							</tr>
						</thead>
						<tbody>

						<c:forEach items="${products}" var="entry">
						    <c:set var="product" value="${entry.product}"/>
							<tr class="rem1">
								<td class="invert">
									${product.id}
								</td>
								<td class="invert-image">
									<a href="single.html">
										<img src="${pageContext.request.contextPath}/${product.image}" alt=" " class="img-responsive">
									</a>
								</td>
								<td class="invert">
									<div class="quantity">
										<div class="quantity-select">
											<div class="entry value">
												<span>
												    ${entry.amount}
												</span>
											</div>
										</div>
									</div>
								</td>
								<td class="invert">
								${product.name}
								</td>
								<td class="invert">
								    ${product.price}
								</td>
							</tr>
						</c:forEach>
						</tbody>
					</table>

                    <p><input type='radio' name='paymentKind' value='cash' onclick="hideFields(true)" checked> Cash</p>
                    <p><input type='radio' name='paymentKind' value='bankCard' onclick="hideFields(false)"> Bank Card</p>

                    <div id='hidden_fields'>
                            <p>Number: <input type="text" name="bankCard" value=""></p>
                    </div>

                </form>

                <div class="controls form-group">
                    <select name="delivery" class="option-w3ls" form="form1">
                        <option>Select Delivery</option>
                        <option value="home">Home</option>
                        <option value="department">From Department</option>
                    </select>
                </div>

					<button type="submit" form="form1" class="submit check_out btn">Delivery to this Address</button>
				</div>
			</div>

		</div>
	</div>
