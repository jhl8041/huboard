<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <title>TensorFlow Java API object detection sample</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css"
          integrity="sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ" crossorigin="anonymous"/>
    <script src="https://code.jquery.com/jquery-3.1.1.slim.min.js"
            integrity="sha384-A7FZj7v+d/sdmMqp/nOQwliLvUsJfDHW+k9Omg/a/EheAdgtzNs3hpfag6Ed950n" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js"
            integrity="sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js"
            integrity="sha384-vBWWzlZJ8ea9aCX4pEW3rVHjgjt7zpkNpZk+02D9phzyeVkE+jo0ieGizqPLForn" crossorigin="anonymous"></script>
</head>
    <body>
        <div class="navbar navbar-fixed-top navbar-inverse bg-inverse">
            <a href="/" title="TensorFlow Java API with YOLO" class="navbar-brand">TensorFlow Java API with YOLO</a>
        </div>
        <div class="container">
        	<c:if test="${originalName}">
	            <div class="row mt-4">
	                <p>
	                    <span>Image: </span><span>${originalName}</span><span> has been successfully uploaded!</span>
	                </p>
	            </div>
            </c:if>
            <div class="row">
                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th>Input image</th>
                            <th>Result</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td><img src="${originalImage}" title="${originalImage}" alt="${originalImage}" width="416" /></td>
                            <td><img src="${predictedImage}" title="${predictedImage}" alt="${predictedImage}" width="416" /></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td>
                                <ul>
                                    <c:forEach var="recognition" items="${recognitions}">
                                        <li><span>${recognition.title}</span><span> - </span><span>${recognition.confidence}</span></li>
                                    </c:forEach>
                                </ul>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </body>
</html>