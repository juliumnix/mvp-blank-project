import React from 'react';
import { Button, StyleSheet, Text, View} from 'react-native';
import { NativeProps } from '../..';

export const App = (props : NativeProps)  => {
    return (
      <View style={styles.container}>
        <Text style={styles.hello}>Welcome to React Native</Text>
        <Text style={styles.hello}>
          This is the message coming from the native
        </Text>
        <Text style={styles.nativeText}>{props.message_from_native}</Text>
      </View>
    );
  };
  
  const styles = StyleSheet.create({
    container: {
      flex: 1,
      justifyContent: 'center',
    },
    hello: {
      fontSize: 20,
      textAlign: 'center',
      margin: 10,
    },
    nativeText: {
      fontSize: 20,
      textAlign: 'center',
      fontWeight: 'bold'
    }
  });