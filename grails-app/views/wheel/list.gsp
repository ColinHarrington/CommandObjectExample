
<%@ page import="com.example.Wheel" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'wheel.label', default: 'Wheel')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                            <g:sortableColumn property="id" title="${message(code: 'wheel.id.label', default: 'Id')}" />
                        
                            <g:sortableColumn property="size" title="${message(code: 'wheel.size.label', default: 'Size')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${wheelInstanceList}" status="i" var="wheelInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${wheelInstance.id}">${fieldValue(bean: wheelInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: wheelInstance, field: "size")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${wheelInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
