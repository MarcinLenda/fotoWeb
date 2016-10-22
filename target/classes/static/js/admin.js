/**
 * Created by Promar on 08.10.2016.
 */
// app.controller('home',
//
//     function($http) {
//
//         $http.get('/myAccount/user', {
//             headers : headers
//         }).then(function(response) {
//             var data = response.data;
//             if (data.name) {
//                 console.log('huraaa');
//                 self.authenticated = true;
//                 self.user = data.name
//                 self.admin = data && data.roles && data.roles.indexOf("ROLE_ADMIN")>-1;
//             } else {
//                 self.authenticated = false;
//                 self.admin = false;
//             }
//             callback && callback(true);
//         }, function() {
//             self.authenticated = false;
//             callback && callback(false);
//         })
//
//     });