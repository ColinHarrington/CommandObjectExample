<html>
    <head>
        <title>Welcome to Grails</title>
        <meta name="layout" content="main" />
    </head>
    <body>
        <div id="pageBody">
            <g:form controller="my" action="changeWheel">
                <label>Car ID:</label> <g:textField name="car.id"/><br/>
                <label>Wheel ID:</label> <g:textField name="wheel.id"/><br/>
                <g:submitButton name="Submit" value="Submit"/>
            </g:form>
        </div>
    </body>
</html>
