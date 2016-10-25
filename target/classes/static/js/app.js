/**
 * Created by Promar on 10.09.2016.
 */
'use strict';

// Declare app level module which depends on views, and components
var app = angular.module('myApp', [
    'ngRoute', 'ngResource'

]).config(function($routeProvider, $httpProvider) {

    $routeProvider
        .when('/', {
            templateUrl: 'views/home.html',
            controller: 'navigation'
        })
        .when('/aboutme', {
            templateUrl: 'views/aboutme.html',
            controller: 'navigation'
        })
        .when('/login', {
            templateUrl: 'views/login.html',
            controller: 'navigation',
            controllerAs: 'controller'
        })
        .when('/logout', {
            templateUrl: 'views/home.html',
            controller: 'navigation'
        })
        .when('/gallery', {
            templateUrl: 'views/gallery.html',
            controller: 'albumCtrl'
        })
        .when('/gallery/album1', {
            templateUrl: 'views/album/album1.html',
            controller: 'albumCtrl'
        })
        .when('/gallery/album2', {
            templateUrl: 'views/album/album2.html',
            controller: 'albumCtrl'
        })
        .when('/gallery/album3', {
            templateUrl: 'views/album/album3.html',
            controller: 'albumCtrl'
        })
        .when('/gallery/album4', {
            templateUrl: 'views/album/album4.html',
            controller: 'albumCtrl'
        })
        .when('/register', {
            templateUrl: 'views/register.html',
            controller: 'registerCtrl'
        })
        .when('/contact', {
            templateUrl: 'views/contact.html',
            controller: 'mailCtrl'
        })
        .when('/admin', {
            templateUrl: 'views/admin.html',
            controller: 'navigation'
        })
        .when('/info', {
            templateUrl: 'views/info.html',
            controller: 'registerCtr'
        }).otherwise({redirectTo: '/'});

    $httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';
})
