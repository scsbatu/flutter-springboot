import 'package:firebase_auth/firebase_auth.dart';
import 'package:flutter/material.dart';
import 'package:flutterapp/service/auth.dart';

class Home extends StatefulWidget {
  @override
  _DashboardPageState createState() => _DashboardPageState();
}

class _DashboardPageState extends State<Home> {
  String textHolder = 'Welcome to your mobile app';

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        backgroundColor: Colors.cyan,
        body: Center(
            child: Column(
              mainAxisAlignment: MainAxisAlignment.center,
              children: <Widget>[
                Container(
                  padding: EdgeInsets.fromLTRB(20, 20, 20, 20),
                  child: Text(
                    '$textHolder',
                    style: TextStyle(fontSize: 21),
                  ),
                ),
                RaisedButton(
                    child: Text('Access REST'),
                    color: Colors.blueAccent,
                    onPressed: () => displaySecureResource()
                ),
                RaisedButton(
                  child: Text('Logout'),
                  color: Colors.greenAccent,
                  onPressed: () {
                    AuthService().signOut();
                  },
                ),
              ],
            )));
  }

  displaySecureResource() async {
    String response = await AuthService().extractTokenAndAccessSecureResource();
    setState(() {
      textHolder = response;
    });
  }
}
