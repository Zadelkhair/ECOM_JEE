<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>

    <div class="nav flex-column nav-pills" aria-orientation="vertical">
        <a class="nav-link myaccount-nav-link" href="${MyConfig.getHost()}myaccount/order/"><i class="fa fa-shopping-bag"></i>Orders</a>
        <a class="nav-link myaccount-nav-link" href="${MyConfig.getHost()}myaccount/details/"><i class="fa fa-user"></i>Account Details</a>
    </div>

    <script>

        let myaccountNavLinks = document.querySelectorAll('.myaccount-nav-link');

        myaccountNavLinks.forEach((link) => {

            let linkHref = link.href.split('?')[0];
            let locationHref = window.location.href.split('?')[0];

            if (linkHref[linkHref.length - 1] == '/') {
                linkHref = linkHref.substr(0, linkHref.length - 1);
            }

            if (locationHref[locationHref.length - 1] == '/') {
                locationHref = locationHref.substr(0, locationHref.length - 1);
            }

            console.log(linkHref, '\\', locationHref, '\\', window.location.href.split('?')[0]);

            if (linkHref == locationHref)
                link.classList.add('active');

        })

    </script>