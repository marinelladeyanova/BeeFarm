
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>


<!DOCTYPE html>
<html>
<%@ include file="include-files/head.jspf" %>
<body>
<c:redirect url="/"/>

<!-- Preloader -->
<div id="preloader">
    <div id="status" class="la-ball-triangle-path">
        <div></div>
        <div></div>
        <div></div>
    </div>
</div>
<!--End of Preloader-->

<div class="page-border" data-wow-duration="0.7s" data-wow-delay="0.2s">
    <div class="top-border wow fadeInDown animated" style="visibility: visible; animation-name: fadeInDown;"></div>
    <div class="right-border wow fadeInRight animated" style="visibility: visible; animation-name: fadeInRight;"></div>
    <div class="bottom-border wow fadeInUp animated" style="visibility: visible; animation-name: fadeInUp;"></div>
    <div class="left-border wow fadeInLeft animated" style="visibility: visible; animation-name: fadeInLeft;"></div>
</div>

<div id="wrapper">

    <header id="banner" class="scrollto clearfix" data-enllax-ratio=".5">
        <div id="header" class="nav-collapse">
            <div class="row clearfix">
                <div class="col-1">

                    <!--Logo-->
                    <div id="logo">

                        <!--Logo that is shown on the banner-->
                        <img src="images/logo-7.jpg" id="banner-logo" alt="Landing Page"/>
                        <!--End of Banner Logo-->

                        <!--The Logo that is shown on the sticky Navigation Bar-->
                        <img src="images/logo-7.jpg" id="navigation-logo" alt="Landing Page"/>
                        <!--End of Navigation Logo-->

                    </div>
                    <!--End of Logo-->
                    <%@ include file="include-files/socialMediaIcons.jspf" %>

                    <!--Main Navigation-->
                    <%@ include file="include-files/mainMenuNavigation.jspf" %>
                    <!--End of Main Navigation-->

                    <div id="nav-trigger"><span></span></div>
                    <nav id="nav-mobile"></nav>
                </div>
            </div>
        </div><!--End of Header-->

        <!--Banner Content-->
        <%@ include file="include-files/banner.jsp"%><!--End of Row-->
    </header>

    <!--Main Content Area-->
    <main id="content">

        <%@ include file="include-files/content-za-nashite-pcheli.jspf" %>
        <%@ include file="include-files/content-za-porodata.jspf" %>
        <%@ include file="include-files/gallery.jspf" %>
        <%@ include file="include-files/content-selection.jspf" %>
        <%@ include file="include-files/content-feedback.jspf" %>
        <%@ include file="include-files/content-ourClients.jspf" %>
        <%@ include file="include-files/contetnt-pricingTable.jspf" %>

    </main>
    <!--End Main Content Area-->


    <!--Footer-->
    <%@ include file="include-files/footer.jspf" %>
    <!--End of Footer-->

</div>

<!-- Include JavaScript resources -->
<script src="js/jquery.1.8.3.min.js"></script>
<script src="js/wow.min.js"></script>
<script src="js/featherlight.min.js"></script>
<script src="js/featherlight.gallery.min.js"></script>
<script src="js/jquery.enllax.min.js"></script>
<script src="js/jquery.scrollUp.min.js"></script>
<script src="js/jquery.easing.min.js"></script>
<script src="js/jquery.stickyNavbar.min.js"></script>
<script src="js/jquery.waypoints.min.js"></script>
<script src="js/images-loaded.min.js"></script>
<script src="js/lightbox.min.js"></script>
<script src="js/site.js"></script>


</body>
</html>
