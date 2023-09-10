import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      debugShowCheckedModeBanner: false,
      home: const MyHomePage(),
    );
  }
}

class MyHomePage extends StatefulWidget {
  const MyHomePage({super.key});

  @override
  State<MyHomePage> createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> with WidgetsBindingObserver {
  String _message = '';
  static const platform = MethodChannel('samples.flutter.dev/battery');

  Future<void> _getMessage() async {
    String batteryLevel;
    try {
      final String result = await platform.invokeMethod('getMessage');
      batteryLevel = result;
    } on PlatformException catch (e) {
      batteryLevel = "Failed to get message from native: '${e.message}'.";
    }

    setState(() {
      _message = batteryLevel;
    });
  }

  @override
  void initState() {
    super.initState();
    WidgetsBinding.instance.addObserver(this);
  }

  @override
  void dispose() {
    WidgetsBinding.instance.removeObserver(this);
    super.dispose();
  }

  @override
  void didChangeAppLifecycleState(AppLifecycleState state) {
    if (state == AppLifecycleState.resumed) {
      // Função a ser executada quando a tela é focada
      print('Tela focada! Executando função...');
      _getMessage();
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          crossAxisAlignment: CrossAxisAlignment.center,
          children: [
            const Text("Welcome to Flutter", style: TextStyle(fontSize: 20)),
            Container(height: 10),
            const Text("This is the message coming from the native",
                style: TextStyle(fontSize: 16)),
            Container(height: 10),
            Text(_message,
                style:
                    const TextStyle(fontSize: 18, fontWeight: FontWeight.bold)),
          ],
        ),
      ),
    );
  }
}
