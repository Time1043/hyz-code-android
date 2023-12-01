// IPersonInterface.aidl
package com.example.aidltest;

import com.example.aidltest.Person;
import com.example.aidltest.ICallback;

// Declare any non-default types here with import statements

interface IPersonInterface {

   void addPerson(in Person person);
   void addPersonOut(out Person person);
   void addPersonInOut(inout Person person);
   List<Person> getPersonList();
   void addCallback(in ICallback callback);
   void removeCallback();
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);
}