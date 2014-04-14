<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<html lang="en">
<head>
<title>Search Evaluation Page</title>
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/angularjs/1.2.3/angular.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/underscore.js/1.5.2/underscore-min.js"></script>
<script type="text/javascript" src="<c:url value="/assets/js/taggedInfiniteScroll.min.js" />"></script>
<link type="text/css" rel="stylesheet" href="<c:url value="/assets/css/bootstrap.css" />"/>
</head>
<body ng-app="SearchEvaluation" ng-cloak>
<style type="text/css">
dl.fields{
    clear: left;
    margin: 0;
    padding: 18px 0;
    list-style-type: none;
}

dl.fields dt{
    width: 140px;
    float: left;
    clear: left;
}

dl.fields dd{
    margin-left: 150px;
}

ul.items{
    list-style-type: none;
    margin: 0;
    padding: 0;
}

ul.items li{
    padding: 10px;
    margin: 5px 0;
}

.paginating{
  opacity: 0.5;
}
</style>

<div class="container" ng-controller="SearchEvaluationController">

    <ul>
        <li class="err" ng-repeat="error in errors"> {{ error}} </li>
    </ul>

    <div class="row">
        <div class="col-lg-12">

            <div class="bs-component">

                <ul>
                    <li class="err" ng-repeat="error in errors"> {{ error}} </li>
                </ul>

                <div class="navbar navbar-default">
                    <div class="navbar-header">
                        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-responsive-collapse">
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </button>
                        <a class="navbar-brand" href="#">NutraSpace</a>


                    </div>

                    <div class="navbar-header">
                        <ul class="nav navbar-nav">
                        <li><a href="#">Stats</a></li>
                        </ul>
                     </div>

                    <div class="navbar-collapse collapse navbar-responsive-collapse">
                        <form class="navbar-form navbar-left">
                            <input type="text" class="form-control col-lg-8" id="searchTextBoxId" placeholder="Search"/>
                            <button type="button" class="btn btn-info" ng-click="getResults()">Search</button>
                        </form>
                        <ul class="nav navbar-nav navbar-right">
                            <li><a href="<c:url value="/j_spring_security_logout" />">Logout</a></li>

                            <li><a href="http://www.nutraspace.com" target="_blank">Go to nutraspace.com</a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>


  <div >
    <div tagged-infinite-scroll="getMore()" tagged-infinite-scroll-disabled="!enabled || paginating" tagged-infinite-scroll-distance="distance">
      <ul class="items" ng-class="{ paginating: paginating }">
        <li ng-repeat="item in items" >
            <div class="well well-sm">
                  <p><h3>{{ item.title }}</h3><span class="label label-default" id="rating_{{$index}}">{{ item.rating }}</span></p>


                    <table>
                        <tr>
                          <td><button type="button" name="RELEVANT" class="btn btn-success btn-xs" ng-click="registerClick($index, $event)">Very Relevant</button></td>
                          <td><button type="button" name="PASSABLE" class="btn btn-info btn-xs" ng-click="registerClick($index, $event)">Passable</button></td>
                          <td><button type="button" name="IRRELEVANT" class="btn btn-danger btn-xs" ng-click="registerClick($index, $event)">Irrelevant</button></td>
                        </tr>
                    </table>

                    <table>

                        <tr>
                            <td>&nbsp;</td>

                        </tr>

                        <tr>
                            <td>{{ item.highlightedContent }}</td>
                        </tr>

                        <tr>
                            <td>&nbsp;</td>
                        </tr>

                        <tr>
                            <td><a href="{{item.url}}" target="_blank" id="url_{{$index}}"><small>{{ item.url }}</small></a></td>
                        </tr>

                        <tr>
                            <td>&nbsp;</td>
                        </tr>

                        <tr>
                            <td><textarea class="form-control" rows="2" placeholder="Enter your comment" id="comment_{{$index}}"></textarea></td>
                        </tr>

                    </table>
            </div>

        </li>
      </ul>
    </div>
  </div>
</div>

<script type="text/javascript">
<!--
var ngeval = angular.module('SearchEvaluation', ['tagged.directives.infiniteScroll']);

function SearchEvaluationController($scope, $http) {
  $scope.items = [];
  $scope.distance = 0;
  $scope.paginating = false;
  $scope.enabled = true;

  $scope.errors = [];

  var parameter=null;



  // This is called each time the infinite scroll directive needs to get more items.
  $scope.getResults = function() {

      console.log("In get results method")
    if ($scope.items.length >= 200) return;

    if (true === $scope.paginating) return;

    var query=angular.element(document.querySelector("#searchTextBoxId")).val();
    parameter=query;
    console.log("get results param :"+parameter);

    //var timeout = ($scope.items.length ? 1000 : 0);
    $scope.paginating = true;

      var pageNo=1;

      console.log("Scope items length : "+$scope.items.length)

      if ($scope.items.length<10){
          pageNo=1;
      }
      else{
          pageNo=($scope.items.length /10)+1;
      }

      console.log("page no : "+pageNo);

      //$.getJSON( '/searchcontroller/search?query=omega&page='+pageNo, function( data ) {

      var fetchUrl='<c:url value="/search/fetch" />'+'?query='+parameter+'&page='+pageNo;
      console.log("Fetch URL : "+fetchUrl);
      $http.get(fetchUrl).success(function(data) {


          console.log("data::: "+   data.webHits.results[0].id);

          console.log ("pageno" +pageNo);

          if (pageNo===1){
              $scope.items=data.webHits.results;
          }
          else{

              $scope.items=$scope.items.concat(data.webHits.results);
              console.log("new scope items length : "+$scope.items.length);

          }

          $scope.paginating = false;

          console.log("pushed webhits to scope") ;

      });

  };


    $scope.getMore= function() {

        console.log ("get more param : "+parameter);
        if (parameter===null) return;
        $scope.getResults(parameter);
    }

  //$scope.getMore();

  $scope.reset = function() {
    $scope.items = [];
    $scope.getMore();
  };


    $scope.registerClick = function(id, event) {

        console.log("current id : "+id);

        var ratingString=event.target.name;
        var url=angular.element(document.querySelector('#url_'+id)).children("small").first().html();
        var comments=angular.element(document.querySelector('#comment_'+id)).val();


        console.log ("Parameter and URL : " + parameter + ":::"+ url +":::" + ratingString +":::"+comments);


        $scope.errors.splice(0, $scope.errors.length); // remove all error messages


        var requestData={query: parameter, url: url, ratingString:ratingString, comments: comments};

        var persistUrl='<c:url value="/search/persist" />';

        $http.post(persistUrl,requestData, {
            headers: { 'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'},
            transformRequest: function(obj) {
                var str = [];
                for(var p in obj)
                    str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
                return str.join("&");
            }
            //{query: $scope.parameter, url: url, ratingString:ratingString, comments: comments})
            }).success(function(data, status, headers, config) {
                    if (data.msg != ''){
                        //$scope.msgs.push(data.msg);
                        //event.target.
                        angular.element(document.querySelector('#rating_'+id)).text(data);
                    }
                    else{
                        $scope.errors.push(data.error);
                    }
        }).error(function(data, status) { // called asynchronously if an error occurs
// or server returns response with an error status.
            $scope.errors.push(status);
        });
    }

}
//-->
</script>

</body>
</html>
