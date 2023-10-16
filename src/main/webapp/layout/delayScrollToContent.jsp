<%-- Created on : Oct 5, 2023, 6:14:37 PM by DuyDuc94 --%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script>
    //Auto scroll to the content of page with delay
    document.getElementById("content-display").scrollIntoView({behavior: 'instant'});
    location.hash = '#content';
</script>