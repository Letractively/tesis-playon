<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	
	<div id="slider">
		<div class="viewer">
			<div class="reel">
				<div class="slide">
					<img src="<c:url value="/resources/images/slide01.jpg" />" alt="" />
				</div>
				<div class="slide">
					<img src="<c:url value="/resources/images/slide02.jpg" />" alt="" />
				</div>
				<div class="slide">
					<img src="<c:url value="/resources/images/slide03.jpg" />" alt="" />
				</div>
				<div class="slide">
					<img src="<c:url value="/resources/images/slide04.jpg" />" alt="" />
				</div>
				<div class="slide">
					<img src="<c:url value="/resources/images/slide05.jpg" />" alt="" />
				</div>
			</div>
		</div>
		<div class="indicator">
			<span>1</span>
			<span>2</span>
			<span>3</span>
			<span>4</span>
			<span>5</span>
		</div>
	</div>