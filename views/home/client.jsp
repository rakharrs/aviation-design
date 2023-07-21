
<%@ page import="model.vol.Vol" %>
<%@ page import="model.vol.Reservation" %>
<%
    Vol[] vols = (Vol[]) request.getAttribute("vols");
    String token = request.getParameter("token");
    Reservation[] reservations = (Reservation[]) request.getAttribute("reservations");
    String error = request.getParameter("error");
%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" type="image/png" sizes="16x16" href="plugins/images/favicon.png">
    <title>Avion-mi</title>
    <!-- Bootstrap Core CSS -->
    <link href="./assets/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Menu CSS -->
    <link href="./assets/plugins/bower_components/sidebar-nav/dist/sidebar-nav.min.css" rel="stylesheet">
    <!-- animation CSS -->
    <link href="./assets/css/animate.css" rel="stylesheet">
    <!-- Custom CSS -->
    <link href="./assets/css/style.css" rel="stylesheet">
    <!-- color CSS -->
    <link href="./assets/css/colors/default.css" id="theme" rel="stylesheet">
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
<![endif]-->
</head>
<script>
    <% if(token != null){ %>
        alert("Le token de votre reservation est : <%=token%>")
    <% } %>
</script>
<body class="fix-header">


    <!-- ============================================================== -->
    <!-- Preloader -->
    <!-- ============================================================== -->
    <div class="preloader">
        <svg class="circular" viewBox="25 25 50 50">
            <circle class="path" cx="50" cy="50" r="20" fill="none" stroke-width="2" stroke-miterlimit="10" />
        </svg>
    </div>
    <!-- ============================================================== -->
    <!-- Wrapper -->
    <!-- ============================================================== -->
    <div id="wrapper">
        <!-- ============================================================== -->
        <!-- Topbar header - style you can find in pages.scss -->
        <!-- ============================================================== -->
        <nav class="navbar navbar-default navbar-static-top m-b-0">
            <div class="navbar-header">
                <div class="top-left-part">
                    <!-- Logo -->
                    <h3 style="text-align: center;"><b >Gestion avion</b></h3><hr>
                </div>
                <!-- /Logo -->
                <ul class="nav navbar-top-links navbar-right pull-right">
                </ul>
            </div>
            <!-- /.navbar-header -->
            <!-- /.navbar-top-links -->
            <!-- /.navbar-static-side -->
        </nav>
        <!-- End Top Navigation -->
        <!-- ============================================================== -->
        <!-- Left Sidebar - style you can find in sidebar.scss  -->
        <!-- ============================================================== -->
        <div class="navbar-default sidebar" role="navigation">
            <div class="sidebar-nav slimscrollsidebar">
                <div class="sidebar-head">
                    <h3><span class="fa-fw open-close"><i class="ti-close ti-menu"></i></span> <span class="hide-menu">Navigation</span></h3>
                </div>
                <ul class="nav" id="side-menu">
                    <li style="padding: 70px 0 0;">
                        <a href="#vols" class="waves-effect"><i class="fa fa-clock-o fa-fw" aria-hidden="true"></i>Liste vol(s)</a>
                    </li>
                    <li>
                        <a href="#reservations" class="waves-effect"><i class="fa fa-clock-o fa-fw" aria-hidden="true"></i>Liste reservation(s)</a>
                    </li>
                    <div class="center p-20">
                        <a href="/avion-mi/logout" target="_blank" class="btn btn-danger btn-block waves-effect waves-light">SIGN OUT</a>
                    </div>
                </ul>
            </div>
        </div>
        <!-- ============================================================== -->
        <!-- End Left Sidebar -->
        <!-- ============================================================== -->
        <!-- ============================================================== -->
        <!-- Page Content -->
        <!-- ============================================================== -->
        <div id="page-wrapper">
            <div class="container-fluid">
                <div class="row bg-title">
                    <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
                        <h4 class="page-title">CLIENT</h4>
                        <% if(error != null){ %>
                            <h3 style="color: red;"><%=error%></h3>
                        <% } %> 
                    </div>
                </div>
                <div class="row" id="vols">
                    <div class="col-md-12">
                        <div class="white-box">
                            <h3 class="box-title">Liste de(s) vol(s) disponible</h3>
                            <hr>
                            <div class="table-responsive">
                                <table class="table">
                                    <thead>
                                        <tr>
                                            <th>#</th>
                                            <th>Avion</th>
                                            <th>Lieu de depart</th>
                                            <th>Lieu d'arrive</th>
                                            <th>Date vol</th>
                                            <th>Place eco</th>
                                            <th>Place affaire</th>
                                            <th>Vente de billet</th>
                                            <th>Fin vente</th>
                                            <th>Trajet</th>
                                            <th>Reserver</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    <% for(Vol vol : vols){ %>
                                        <tr>
                                            <td><%=vol.getId()%></td>
                                            <td><%=vol.getNom_avion()%></td>
                                            <td><%=vol.getLieu_depart()%></td>
                                            <td><%=vol.getLieu_arrive()%></td>
                                            <td><%=vol.getDate_vol()%></td>
                                            <td><%=vol.getPlace_economique()%> - <span style="text-decoration: underline"><%=vol.getPrix_eco()%>ar/u</span></td>
                                            <td><%=vol.getPlace_affaire()%> - <span style="text-decoration: underline"><%=vol.getPrix_affaire()%>ar/u</span></td>
                                            <td><%=vol.getDebut_vente()%></td>
                                            <td><%=vol.getFin_vente()%></td>
                                            <td><%=vol.aller_retour()%></td>
                                            <td style="text-align: center">
                                                <button data-toggle="modal" data-target="#myModal<%=vol.getId()%>">
                                                    <i class="fa fa-bookmark fa-lg"></i>
                                                </button>
                                            </td>
                                        </tr>
                                        <div id="myModal<%=vol.getId()%>" class="modal fade" role="dialog">
                                            <div class="modal-dialog">
                                                <!-- Modal content -->
                                                <div class="modal-content">
                                                    <div class="modal-header">
                                                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                                                        <h4 class="modal-title">Resaka reservation</h4>
                                                    </div>
                                                    <form action="reservation" method="post" class="form-material form-horizontal">
                                                        <div class="modal-body">
                                                            <input style="display: none;" name="id_vol" value="<%=vol.getId()%>">
                                                            <div class="form-group">
                                                                <label>Nom</label>
                                                                <input class="form-control" type="text" name="nom">
                                                            </div>
                                                            <div class="form-group">
                                                                <label>Date de reservation</label>
                                                                <input class="form-control" type="datetime-local" name="date">
                                                            </div>
                                                            <div class="form-check">
                                                                <input class="form-check-input" type="checkbox" name="affaire" value="true">
                                                                <label class="form-check-label">Buisness class</label>
                                                            </div>
                                                        </div>
                                                        <div class="modal-footer">
                                                            <button type="submit" class="btn btn-success">Reserver</button>
                                                        </div>
                                                    </form>
                                                </div>
                                            </div>
                                        </div>
                                    <% } %>
                                    </tbody>
                                </table>

                            </div>
                        </div>
                    </div>
                </div>
                <div class="row" id="reservations">
                    <div class="col-md-12">
                        <div class="white-box">
                            <h3 class="box-title">Liste de(s) reservation(s)</h3>
                            <hr>
                            <div class="table-responsive">
                                <table class="table">
                                    <thead>
                                        <tr>
                                            <th>#</th>
                                            <th>Nom</th>
                                            <th># vol</th>
                                            <th>place eco</th>
                                            <th>place affaire</th>
                                            <th>prix</th>
                                            <th>date</th>
                                            <th>Annuler</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    <% for(Reservation reservation : reservations){ %>
                                        <tr>
                                            <td><%=reservation.getId()%></td>
                                            <td><%=reservation.getNom()%></td>
                                            <td><%=reservation.getId_vol()%></td>
                                            <td><%=reservation.getPlace_eco()%></td>
                                            <td><%=reservation.getPlace_affaire()%></td>
                                            <td><%=reservation.getPrix()%></td>
                                            <td><%=reservation.getDate()%></td>
                                            <td>
                                                <button data-toggle="modal" data-target="#ModalUndo<%=reservation.getId()%>">
                                                    <i class="fa fa-undo fa-lg"></i>
                                                </button>
                                            </td>
                                        </tr>
                                        <div id="ModalUndo<%=reservation.getId()%>" class="modal fade" role="dialog">
                                            <div class="modal-dialog">
                                                <!-- Modal content -->
                                                <div class="modal-content">
                                                    <div class="modal-header">
                                                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                                                        <h4 class="modal-title">Resaka annuler reservation</h4>
                                                    </div>
                                                    <form action="reservation/annuler" method="get" class="form-material form-horizontal">
                                                        <div class="modal-body">
                                                            <input style="display: none;" name="id_reservation" value="<%=reservation.getId()%>">
                                                            <div class="form-group">
                                                                <label>Token</label>
                                                                <input class="form-control" type="text" name="n_token">
                                                            </div>
                                                            <div class="form-group">
                                                                <label>Date d'annulation'</label>
                                                                <input class="form-control" type="datetime-local" name="date_annulation">
                                                            </div>
                                                        </div>
                                                        <div class="modal-footer">
                                                            <button type="submit" class="btn btn-danger">Annuler</button>
                                                        </div>
                                                    </form>
                                                </div>
                                            </div>
                                        </div>
                                    <% } %>
                                    </tbody>
                                </table>

                            </div>
                        </div>
                    </div>
                </div>

            </div>
            <!-- /.container-fluid -->
            <footer class="footer text-center"> Gestion aviation - 2023 </footer>
        </div>
        <!-- ============================================================== -->
        <!-- End Page Content -->
        <!-- ============================================================== -->
    </div>
    <!-- /#wrapper -->
    <!-- jQuery -->
    <script src="./assets/plugins/bower_components/jquery/dist/jquery.min.js"></script>
    <!-- Bootstrap Core JavaScript -->
    <script src="./assets/bootstrap/dist/js/bootstrap.min.js"></script>
    <!-- Menu Plugin JavaScript -->
    <script src="./assets/plugins/bower_components/sidebar-nav/dist/sidebar-nav.min.js"></script>
    <!--slimscroll JavaScript -->
    <script src="./assets/js/jquery.slimscroll.js"></script>
    <!--Wave Effects -->
    <script src="./assets/js/waves.js"></script>
    <!-- Custom Theme JavaScript -->
    <script src="./assets/js/custom.min.js"></script>
</body>

</html>
