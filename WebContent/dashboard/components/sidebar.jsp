<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>

    <%@ page import="com.ecomjeegi.MyConfig" %>

        <div class="nav flex-column nav-pills" aria-orientation="vertical">
            <a class="nav-link dash-nav-link" id="dashboardlink" href="${MyConfig.getHost()}dashboard"><i
                    class="fa fa-tachometer-alt"></i>Dashboard</a>
            <a class="nav-link dash-nav-link" id="dashboardorderlink" href="${MyConfig.getHost()}dashboard/order"><i
                    class="fas fa-shopping-cart"></i>Orders</a>
            <!-- <a class="nav-link dash-nav-link" id="dashboardorderdetailslink"
                href="${MyConfig.getHost()}dashboard/orderdetails"><i class="fa fa-shopping-bag"></i>Orders details</a> -->
            <a class="nav-link dash-nav-link" id="dashboardcategorielink"
                href="${MyConfig.getHost()}dashboard/categorie"><i class="fa fa-list-alt"></i>Categories</a>
            <a class="nav-link dash-nav-link" id="dashboardproductlink" href="${MyConfig.getHost()}dashboard/product"><i
                    class="fa fa-object-group" aria-hidden="true"></i>Products</a>
            <a class="nav-link dash-nav-link" id="dashboardadvertisementlink"
                href="${MyConfig.getHost()}dashboard/advertisement"><i class="fas fa-ad"></i>Advertisements</a>
            <a class="nav-link dash-nav-link" id="dashboarduserlink" href="${MyConfig.getHost()}dashboard/user"><i
                    class="fa fa-user" aria-hidden="true"></i>Users</a>
                        <a class="nav-link dash-nav-link" id="dashboarduserlink" href="${MyConfig.getHost()}dashboard/supplier"><i
                    class="fa fa-user" aria-hidden="true"></i>Supplier</a>
            <a class="nav-link dash-nav-link" id="dashboardrolelink" href="${MyConfig.getHost()}dashboard/role"><i
                    class="fa fa-key" aria-hidden="true"></i> Roles</a>
            <a class="nav-link dash-nav-link" id="dashboardpermissionlink"
                href="${MyConfig.getHost()}dashboard/permission"><i class="fas fa-lock"></i>Permissions</a>
            <a class="nav-link dash-nav-link" id="dashboardrolepermissionlink"
                href="${MyConfig.getHost()}dashboard/rolepermission"><i class="fas fa-tools"></i>Role permissions</a>
        </div>

        <script>

            let dashNavLinks = document.querySelectorAll('.dash-nav-link');

            dashNavLinks.forEach((link) => {

                let linkHref = link.href.split('?')[0];
                let locationHref = window.location.href.split('?')[0];

                if (linkHref[linkHref.length - 1] == '/') {
                    linkHref = linkHref.substr(0, linkHref.length - 1);
                }

                if (locationHref[locationHref.length - 1] == '/') {
                    locationHref = locationHref.substr(0, locationHref.length - 1);
                }

                console.log(linkHref,'\\',locationHref,'\\',window.location.href.split('?')[0]);

                if (linkHref == locationHref)
                    link.classList.add('active');

            })

        </script>