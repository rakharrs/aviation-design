
<%@ page import="model.vol.Vol" %>
<%@ page import="model.vol.Reservation" %>
<%@ page import="model.avion.Avion" %>
<%@ page import="model.vol.Lieu" %>
<%
    Vol[] vols = (Vol[]) request.getAttribute("vols");
    Vol[] vols_valide = (Vol[]) request.getAttribute("vols_valide");
    Vol[] vols_invalide = (Vol[]) request.getAttribute("vols_invalide");
    Avion[] avions = (Avion[]) request.getAttribute("avions");
    Lieu[] lieus = (Lieu[]) request.getAttribute("lieus");
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
                        <a href="#planification" class="waves-effect"><i class="fa fa-clock-o fa-fw" aria-hidden="true"></i>Planifier vol</a>
                    </li>
                    <li>
                        <a href="#vol_invalide" class="waves-effect"><i class="fa fa-clock-o fa-fw" aria-hidden="true"></i>Liste vol(s) en attente</a>
                    </li>
                    <li>
                        <a href="#vol_valide" class="waves-effect"><i class="fa fa-clock-o fa-fw" aria-hidden="true"></i>Liste vol(s) valide</a>
                    </li>
                    <li>
                        <a href="#avions" class="waves-effect"><i class="fa fa-clock-o fa-fw" aria-hidden="true"></i>Liste avion</a>
                    </li>
                </ul>
                <div class="center p-20">
                     <a href="/avion-mi/logout" target="_blank" class="btn btn-danger btn-block waves-effect waves-light">SIGN OUT</a>
                 </div>
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
                        <h4 class="page-title">EMP</h4> 
                    </div>
                </div>
                <div class="row" id="planification">
                    <div class="col-md-12">
                        <div class="white-box">
                            <h3 class="box-title">Planifier vol</h3> 
                            <hr>
                            <form action="vol" method="post" class="form-material form-horizontal">
                                <div class="form-check">                                    
                                    <input class="form-check-input" name="aller_retour" type="checkbox" value="true">
                                    <label class="form-check-label">Aller retour :</label>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-12">Avion</label>
                                    <div class="col-sm-12">
                                        <select class="form-control form-control-line" name="id_avion">
                                            <% for(Avion avion : avions){ %>
                                                <option value="<%=avion.getId()%>"><%=avion.getNom()%></option>
                                            <% } %>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-12">Lieu de depart</label>
                                    <div class="col-sm-12">
                                        <select class="form-control form-control-line" name="id_lieu_depart">
                                            <% for(Lieu lieu : lieus){ %>
                                            <option value="<%=lieu.getId()%>"><%=lieu.getDesignation()%></option>
                                            <% } %>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-12">Lieu d'arrive</label>
                                    <div class="col-sm-12">
                                        <select class="form-control form-control-line" name="id_lieu_arrive">
                                            <% for(Lieu lieu : lieus){ %>
                                            <option value="<%=lieu.getId()%>"><%=lieu.getDesignation()%></option>
                                            <% } %>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-12">Duree</label>
                                    <div class="col-md-12">
                                        <input class="form-control form-control-line" type="number" name="duree" value="0">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-12">prix eco</label>
                                    <div class="col-md-12">
                                        <input class="form-control form-control-line" type="number" name="prix_eco" value="0">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-12">prix affaire</label>
                                    <div class="col-md-12">
                                        <input class="form-control form-control-line" type="number" name="prix_affaire" value="0">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-12">Debut vente</label>
                                    <div class="col-md-12">
                                        <input class="form-control form-control-line" type="datetime-local" name="debut_vente">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-12">Fin vente</label>
                                    <div class="col-md-12">
                                        <input class="form-control form-control-line" type="datetime-local" name="fin_vente">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-12">Date vol</label>
                                    <div class="col-md-12">
                                        <input class="form-control form-control-line" type="datetime-local" name="date_vol">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-sm-12">
                                        <button type="submit" class="btn btn-success btn-block">
                                            Planifier
                                        </button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
                <div class="row" id="vol_invalide">
                    <div class="col-md-12">
                        <div class="white-box">
                            <h3 class="box-title">Liste de(s) vol(s) non valide</h3> 
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
                                        </tr>
                                    </thead>
                                    <tbody>
                                    <% for(Vol vol : vols_invalide){ %>
                                        <tr>
                                            <td><%=vol.getId()%></td>
                                            <td><%=vol.getNom_avion()%></td>
                                            <td><%=vol.getLieu_depart()%></td>
                                            <td><%=vol.getLieu_arrive()%></td>
                                            <td><%=vol.getDate_vol()%></td>
                                            <td><%=vol.getPlace_economique()%></td>
                                            <td><%=vol.getPlace_affaire()%></td>
                                            <td><%=vol.getDebut_vente()%></td>
                                            <td><%=vol.getFin_vente()%></td>
                                            <td><%=vol.aller_retour()%></td>
                                        </tr>
                                    <% } %>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row" id="vol_valide">
                    <div class="col-md-12">
                        <div class="white-box">
                            <h3 class="box-title">Liste de(s) vol(s) valide</h3>
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
                                        </tr>
                                    </thead>
                                    <tbody>
                                    <% for(Vol vol : vols_valide){ %>
                                        <tr>
                                            <td><%=vol.getId()%></td>
                                            <td><%=vol.getNom_avion()%></td>
                                            <td><%=vol.getLieu_depart()%></td>
                                            <td><%=vol.getLieu_arrive()%></td>
                                            <td><%=vol.getDate_vol()%></td>
                                            <td><%=vol.getPlace_economique()%></td>
                                            <td><%=vol.getPlace_affaire()%></td>
                                            <td><%=vol.getDebut_vente()%></td>
                                            <td><%=vol.getFin_vente()%></td>
                                            <td><%=vol.aller_retour()%></td>
                                        </tr>
                                    <% } %>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row" id="avions">
                    <div class="col-md-12">
                        <div class="white-box">
                            <h3 class="box-title">Liste de(s) avion(s)</h3>
                            <hr>
                            <div class="table-responsive">
                                <table class="table">
                                    <thead>
                                        <tr>
                                            <th>#</th>
                                            <th>Nom</th>
                                            <th>Place economique</th>
                                            <th>Place affaire</th>
                                            <th>Type</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    <% for(Avion avion : avions){ %>
                                        <tr>
                                            <td><%=avion.getId()%></td>
                                            <td><%=avion.getNom()%></td>
                                            <td><%=avion.getPlace_economique()%></td>
                                            <td><%=avion.getPlace_affaire()%></td>
                                            <td><%=avion.getNom_type()%></td>
                                        </tr>
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
