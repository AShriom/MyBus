package com.app.mybus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import android.content.Context;
import android.os.StrictMode;
import android.util.Log;
import android.widget.Toast;

public class ServletInterface {

    public static String signup(String uname,String fname,String pwd,String dob,String email,String cno)
    {
        final String SERVER_URL = "http://169.254.46.158:8084/mybus/signup"; // The server(Servlet) which will process the request. Note 10.0.2.2 is the localhost IP for emulator
        final String CHAR_SET = "UTF-8"; // Encoding used for the parameters
        OutputStream output = null;
        String response = "";

        try
        {

            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            Log.i("HTTP :", "Preparing data");
            // ------------------------------------------------------START: PREPARE CONNETION AND REQUEST ------------------------------- //

            // Prepare the data string  [ firstName=JOHN&lastName=SMITH ]
            String data = URLEncoder.encode("uname", CHAR_SET) + "=" + URLEncoder.encode(uname, CHAR_SET);
            data += "&" + URLEncoder.encode("fname", CHAR_SET) + "=" + URLEncoder.encode(fname, CHAR_SET);
            data += "&" + URLEncoder.encode("pwd", CHAR_SET) + "=" + URLEncoder.encode(pwd, CHAR_SET);
            data += "&" + URLEncoder.encode("dob", CHAR_SET) + "=" + URLEncoder.encode(dob, CHAR_SET);
            data += "&" + URLEncoder.encode("email", CHAR_SET) + "=" + URLEncoder.encode(email, CHAR_SET);
            data += "&" + URLEncoder.encode("cno", CHAR_SET) + "=" + URLEncoder.encode(cno, CHAR_SET);


            URLConnection connection = new URL(SERVER_URL).openConnection(); // Create a connection to server using URL
            connection.setDoOutput(true); // This means POST method to be used
            connection.setRequestProperty("Accept-Charset", CHAR_SET); //For servers to know what encoding is used for the parameters
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=" + CHAR_SET);

            output = null;
            output = connection.getOutputStream(); //open a Output stream from the connection for posting data
            output.write(data.getBytes(CHAR_SET)); //Post data
            output.flush();
            output.close();
            //------------------------------------------------------ END: PREPARE CONNETION AND REQUEST --------------------------------- //

            Log.i("HTTP :", "sending data");
            InputStream responseStream = connection.getInputStream(); //This is when the request is actually fired



            //------------------------------------------------------ START: READ RESPONSE ------------------------------------------------ //

            Log.i("HTTP :", "Reading response");
            BufferedReader rd = new BufferedReader(new InputStreamReader(responseStream,CHAR_SET)); // Connect a BufferReader to the inputStream
            String line = null;

            while ((line = rd.readLine()) != null) // Read the response line-by-line from the bufferedReader
            {
                response += line;
            }

            //------------------------------------------------------ END: READ RESPONSE ------------------------------------------------- //
            Log.i("response :",response);


        }
        catch (UnsupportedEncodingException e)
        {
            response=response+e.getMessage();
            Log.i("Error1 :",e.getMessage());
            e.printStackTrace();

        }
        catch(IOException io)
        {
            response=response+io.getMessage();
            //Log and check exp
            Log.i("Error2 :",io.getMessage());
        }
        finally
        {
            if (output != null)
                try
                {
                    output.close();
                }
                catch (IOException ignoreIO) {
                    Log.i("Error3 :",ignoreIO.getMessage());
                }
        }
        return response;

    }

    public static String login(String uname,String pass)
    {
        final String SERVER_URL = "http://169.254.46.158:8084/mybus/login"; // The server(Servlet) which will process the request. Note 10.0.2.2 is the localhost IP for emulator
        final String CHAR_SET = "UTF-8"; // Encoding used for the parameters

        OutputStream output = null;
        String response = "";

        try
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            Log.i("HTTP :", "Preparing data");
            // ------------------------------------------------------START: PREPARE CONNECTION AND REQUEST ------------------------------- //

            // Prepare the data string  [ firstName=JOHN&lastName=SMITH ]
            String data = URLEncoder.encode("uname", CHAR_SET) + "=" + URLEncoder.encode(uname, CHAR_SET);
            data += "&" + URLEncoder.encode("pwd", CHAR_SET) + "=" + URLEncoder.encode(pass, CHAR_SET);

            URLConnection connection = new URL(SERVER_URL).openConnection(); // Create a connection to server using URL
            connection.setDoOutput(true); // This means POST method to be used
            connection.setRequestProperty("Accept-Charset", CHAR_SET); //For servers to know what encoding is used for the parameters
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=" + CHAR_SET);


            output = null;
            output = connection.getOutputStream(); //open a Output stream from the connection for posting data
            output.write(data.getBytes(CHAR_SET)); //Post data
            output.flush();output.close();
            //------------------------------------------------------ END: PREPARE CONNETION AND REQUEST --------------------------------- //

            Log.i("HTTP :", "sending data");
            InputStream responseStream = connection.getInputStream(); //This is when the request is actually fired



            //------------------------------------------------------ START: READ RESPONSE ------------------------------------------------ //

            Log.i("HTTP :", "Reading response");
            BufferedReader rd = new BufferedReader(new InputStreamReader(responseStream,CHAR_SET)); // Connect a BufferReader to the inputStream
            String line = null;

            while ((line = rd.readLine()) != null) // Read the response line-by-line from the bufferedReader
            {
                response += line;
            }

            //------------------------------------------------------ END: READ RESPONSE ------------------------------------------------- //
            Log.i("response :",response);


        }
        catch (UnsupportedEncodingException e)
        {
            Log.i("Error1 :",e.getMessage());
            e.printStackTrace();

        }
        catch(IOException io)
        {
            //Log and check exp
            Log.i("Error2 :",io.getMessage());
        }
        finally
        {
            if (output != null) try { output.close(); } catch (IOException ignoreIO) {Log.i("Error3 :",ignoreIO.getMessage());}
        }


        return response;

    }

    public static String addbus(String bid,String bname,String src,String dest,String time,String fare, String cat, String nos)
    {
        final String SERVER_URL = "http://169.254.46.158:8084/mybus/addbus"; // The server(Servlet) which will process the request. Note 10.0.2.2 is the localhost IP for emulator
        final String CHAR_SET = "UTF-8"; // Encoding used for the parameters
        OutputStream output = null;
        String response = "";

        try
        {

            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            Log.i("HTTP :", "Preparing data");
            // ------------------------------------------------------START: PREPARE CONNETION AND REQUEST ------------------------------- //

            // Prepare the data string  [ firstName=JOHN&lastName=SMITH ]
            String data = URLEncoder.encode("bid", CHAR_SET) + "=" + URLEncoder.encode(bid, CHAR_SET);
            data += "&" + URLEncoder.encode("bname", CHAR_SET) + "=" + URLEncoder.encode(bname, CHAR_SET);
            data += "&" + URLEncoder.encode("src", CHAR_SET) + "=" + URLEncoder.encode(src, CHAR_SET);
            data += "&" + URLEncoder.encode("dest", CHAR_SET) + "=" + URLEncoder.encode(dest, CHAR_SET);
            data += "&" + URLEncoder.encode("time", CHAR_SET) + "=" + URLEncoder.encode(time, CHAR_SET);
            data += "&" + URLEncoder.encode("fare", CHAR_SET) + "=" + URLEncoder.encode(fare, CHAR_SET);
            data += "&" + URLEncoder.encode("cat", CHAR_SET) + "=" + URLEncoder.encode(cat, CHAR_SET);
            data += "&" + URLEncoder.encode("nos", CHAR_SET) + "=" + URLEncoder.encode(nos, CHAR_SET);


            URLConnection connection = new URL(SERVER_URL).openConnection(); // Create a connection to server using URL
            connection.setDoOutput(true); // This means POST method to be used
            connection.setRequestProperty("Accept-Charset", CHAR_SET); //For servers to know what encoding is used for the parameters
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=" + CHAR_SET);


            output = null;
            output = connection.getOutputStream(); //open a Output stream from the connection for posting data
            output.write(data.getBytes(CHAR_SET)); //Post data
            output.flush();
            output.close();
            //------------------------------------------------------ END: PREPARE CONNETION AND REQUEST --------------------------------- //

            Log.i("HTTP :", "sending data");
            InputStream responseStream = connection.getInputStream(); //This is when the request is actually fired



            //------------------------------------------------------ START: READ RESPONSE ------------------------------------------------ //

            Log.i("HTTP :", "Reading response");
            BufferedReader rd = new BufferedReader(new InputStreamReader(responseStream,CHAR_SET)); // Connect a BufferReader to the inputStream
            String line = null;

            while ((line = rd.readLine()) != null) // Read the response line-by-line from the bufferedReader
            {
                response += line;
            }

            //------------------------------------------------------ END: READ RESPONSE ------------------------------------------------- //
            Log.i("response :",response);


        }
        catch (UnsupportedEncodingException e)
        {
            response=response+e.getMessage();
            Log.i("Error1 :",e.getMessage());
            e.printStackTrace();

        }
        catch(IOException io)
        {
            response=response+io.getMessage();
            //Log and check exp
            Log.i("Error2 :",io.getMessage());
        }
        finally
        {
            if (output != null)
                try
                {
                    output.close();
                }
                catch (IOException ignoreIO) {
                    Log.i("Error3 :",ignoreIO.getMessage());
                }
        }
        return response;

    }

    public static String reserve(String src,String dest,String date)
    {
        final String SERVER_URL = "http://169.254.46.158:8084/mybus/reservation"; // The server(Servlet) which will process the request. Note 10.0.2.2 is the localhost IP for emulator
        final String CHAR_SET = "UTF-8"; // Encoding used for the parameters
        OutputStream output = null;
        String response = "";

        try
        {

            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            Log.i("HTTP :", "Preparing data");
            // ------------------------------------------------------START: PREPARE CONNETION AND REQUEST ------------------------------- //

            // Prepare the data string  [ firstName=JOHN&lastName=SMITH ]
            String data = URLEncoder.encode("src", CHAR_SET) + "=" + URLEncoder.encode(src, CHAR_SET);
            data += "&" + URLEncoder.encode("dest", CHAR_SET) + "=" + URLEncoder.encode(dest, CHAR_SET);
            data += "&" + URLEncoder.encode("date", CHAR_SET) + "=" + URLEncoder.encode(date, CHAR_SET);


            URLConnection connection = new URL(SERVER_URL).openConnection(); // Create a connection to server using URL
            connection.setDoOutput(true); // This means POST method to be used
            connection.setRequestProperty("Accept-Charset", CHAR_SET); //For servers to know what encoding is used for the parameters
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=" + CHAR_SET);


            output = null;
            output = connection.getOutputStream(); //open a Output stream from the connection for posting data
            output.write(data.getBytes(CHAR_SET)); //Post data
            output.flush();
            output.close();
            //------------------------------------------------------ END: PREPARE CONNETION AND REQUEST --------------------------------- //

            Log.i("HTTP :", "sending data");
            InputStream responseStream = connection.getInputStream(); //This is when the request is actually fired



            //------------------------------------------------------ START: READ RESPONSE ------------------------------------------------ //

            Log.i("HTTP :", "Reading response");
            BufferedReader rd = new BufferedReader(new InputStreamReader(responseStream,CHAR_SET)); // Connect a BufferReader to the inputStream
            String line = null;

            while ((line = rd.readLine()) != null) // Read the response line-by-line from the bufferedReader
            {
                response += line;
            }

            //------------------------------------------------------ END: READ RESPONSE ------------------------------------------------- //
            Log.i("response :",response);


        }
        catch (UnsupportedEncodingException e)
        {
            response=response+e.getMessage();
            Log.i("Error1 :",e.getMessage());
            e.printStackTrace();

        }
        catch(IOException io)
        {
            response=response+io.getMessage();
            //Log and check exp
            Log.i("Error2 :",io.getMessage());
        }
        finally
        {
            if (output != null)
                try
                {
                    output.close();
                }
                catch (IOException ignoreIO) {
                    Log.i("Error3 :",ignoreIO.getMessage());
                }
        }
        return response;

    }

    public static String pay(String nos,String accno,String pwd,String doj,String bid, String fare,String cname)
    {
        final String SERVER_URL = "http://169.254.46.158:8084/mybus/payment"; // The server(Servlet) which will process the request. Note 10.0.2.2 is the localhost IP for emulator
        final String CHAR_SET = "UTF-8"; // Encoding used for the parameters

        OutputStream output = null;
        String response = "";

        try
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            Log.i("HTTP :", "Preparing data");
            // ------------------------------------------------------START: PREPARE CONNECTION AND REQUEST ------------------------------- //

            // Prepare the data string  [ firstName=JOHN&lastName=SMITH ]
            String data = URLEncoder.encode("nos", CHAR_SET) + "=" + URLEncoder.encode(nos, CHAR_SET);
            data += "&" + URLEncoder.encode("accno", CHAR_SET) + "=" + URLEncoder.encode(accno, CHAR_SET);
            data += "&" + URLEncoder.encode("pwd", CHAR_SET) + "=" + URLEncoder.encode(pwd, CHAR_SET);
            data += "&" + URLEncoder.encode("doj", CHAR_SET) + "=" + URLEncoder.encode(doj, CHAR_SET);
            data += "&" + URLEncoder.encode("bid", CHAR_SET) + "=" + URLEncoder.encode(bid, CHAR_SET);
            data += "&" + URLEncoder.encode("fare", CHAR_SET) + "=" + URLEncoder.encode(fare, CHAR_SET);
            data += "&" + URLEncoder.encode("cname", CHAR_SET) + "=" + URLEncoder.encode(cname, CHAR_SET);

            URLConnection connection = new URL(SERVER_URL).openConnection(); // Create a connection to server using URL
            connection.setDoOutput(true); // This means POST method to be used
            connection.setRequestProperty("Accept-Charset", CHAR_SET); //For servers to know what encoding is used for the parameters
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=" + CHAR_SET);


            output = null;
            output = connection.getOutputStream(); //open a Output stream from the connection for posting data
            output.write(data.getBytes(CHAR_SET)); //Post data
            output.flush();output.close();
            //------------------------------------------------------ END: PREPARE CONNETION AND REQUEST --------------------------------- //

            Log.i("HTTP :", "sending data");
            InputStream responseStream = connection.getInputStream(); //This is when the request is actually fired



            //------------------------------------------------------ START: READ RESPONSE ------------------------------------------------ //

            Log.i("HTTP :", "Reading response");
            BufferedReader rd = new BufferedReader(new InputStreamReader(responseStream,CHAR_SET)); // Connect a BufferReader to the inputStream
            String line = null;

            while ((line = rd.readLine()) != null) // Read the response line-by-line from the bufferedReader
            {
                response += line;
            }

            //------------------------------------------------------ END: READ RESPONSE ------------------------------------------------- //
            Log.i("response :",response);


        }
        catch (UnsupportedEncodingException e)
        {
            Log.i("Error1 :",e.getMessage());
            e.printStackTrace();

        }
        catch(IOException io)
        {
            //Log and check exp
            Log.i("Error2 :",io.getMessage());
        }
        finally
        {
            if (output != null) try { output.close(); } catch (IOException ignoreIO) {Log.i("Error3 :",ignoreIO.getMessage());}
        }


        return response;

    }

    public static String ureserve(String cname)
    {
        final String SERVER_URL = "http://169.254.46.158:8084/mybus/ureserv"; // The server(Servlet) which will process the request. Note 10.0.2.2 is the localhost IP for emulator
        final String CHAR_SET = "UTF-8"; // Encoding used for the parameters

        OutputStream output = null;
        String response = "";

        try
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            Log.i("HTTP :", "Preparing data");
            // ------------------------------------------------------START: PREPARE CONNECTION AND REQUEST ------------------------------- //

            // Prepare the data string  [ firstName=JOHN&lastName=SMITH ]
            String data = URLEncoder.encode("cname", CHAR_SET) + "=" + URLEncoder.encode(cname, CHAR_SET);


            URLConnection connection = new URL(SERVER_URL).openConnection(); // Create a connection to server using URL
            connection.setDoOutput(true); // This means POST method to be used
            connection.setRequestProperty("Accept-Charset", CHAR_SET); //For servers to know what encoding is used for the parameters
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=" + CHAR_SET);


            output = null;
            output = connection.getOutputStream(); //open a Output stream from the connection for posting data
            output.write(data.getBytes(CHAR_SET)); //Post data
            output.flush();output.close();
            //------------------------------------------------------ END: PREPARE CONNETION AND REQUEST --------------------------------- //

            Log.i("HTTP :", "sending data");
            InputStream responseStream = connection.getInputStream(); //This is when the request is actually fired



            //------------------------------------------------------ START: READ RESPONSE ------------------------------------------------ //

            Log.i("HTTP :", "Reading response");
            BufferedReader rd = new BufferedReader(new InputStreamReader(responseStream,CHAR_SET)); // Connect a BufferReader to the inputStream
            String line = null;

            while ((line = rd.readLine()) != null) // Read the response line-by-line from the bufferedReader
            {
                response += line;
            }

            //------------------------------------------------------ END: READ RESPONSE ------------------------------------------------- //
            Log.i("response :",response);


        }
        catch (UnsupportedEncodingException e)
        {
            Log.i("Error1 :",e.getMessage());
            e.printStackTrace();

        }
        catch(IOException io)
        {
            //Log and check exp
            Log.i("Error2 :",io.getMessage());
        }
        finally
        {
            if (output != null) try { output.close(); } catch (IOException ignoreIO) {Log.i("Error3 :",ignoreIO.getMessage());}
        }


        return response;

    }

    public static String udetail(String cname,String bid,String doj)
    {
        final String SERVER_URL = "http://169.254.46.158:8084/mybus/getdetail"; // The server(Servlet) which will process the request. Note 10.0.2.2 is the localhost IP for emulator
        final String CHAR_SET = "UTF-8"; // Encoding used for the parameters

        OutputStream output = null;
        String response = "";

        try
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            Log.i("HTTP :", "Preparing data Cname: "+cname+" Bid: "+bid+" DOJ: "+doj);
            // ------------------------------------------------------START: PREPARE CONNECTION AND REQUEST ------------------------------- //

            // Prepare the data string  [ firstName=JOHN&lastName=SMITH ]
            String data = URLEncoder.encode("cname", CHAR_SET) + "=" + URLEncoder.encode(cname, CHAR_SET);
            data += "&" + URLEncoder.encode("bid", CHAR_SET) + "=" + URLEncoder.encode(bid, CHAR_SET);
            data += "&" + URLEncoder.encode("doj", CHAR_SET) + "=" + URLEncoder.encode(doj, CHAR_SET);

            URLConnection connection = new URL(SERVER_URL).openConnection(); // Create a connection to server using URL
            connection.setDoOutput(true); // This means POST method to be used
            connection.setRequestProperty("Accept-Charset", CHAR_SET); //For servers to know what encoding is used for the parameters
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=" + CHAR_SET);


            output = null;
            output = connection.getOutputStream(); //open a Output stream from the connection for posting data
            output.write(data.getBytes(CHAR_SET)); //Post data
            output.flush();output.close();
            //------------------------------------------------------ END: PREPARE CONNETION AND REQUEST --------------------------------- //

            Log.i("HTTP :", "sending data");
            InputStream responseStream = connection.getInputStream(); //This is when the request is actually fired



            //------------------------------------------------------ START: READ RESPONSE ------------------------------------------------ //

            Log.i("HTTP :", "Reading response");
            BufferedReader rd = new BufferedReader(new InputStreamReader(responseStream,CHAR_SET)); // Connect a BufferReader to the inputStream
            String line = null;

            while ((line = rd.readLine()) != null) // Read the response line-by-line from the bufferedReader
            {
                response += line;
            }

            //------------------------------------------------------ END: READ RESPONSE ------------------------------------------------- //
            Log.i("response :",response);


        }
        catch (UnsupportedEncodingException e)
        {
            Log.i("Error1 :",e.getMessage());
            e.printStackTrace();

        }
        catch(IOException io)
        {
            //Log and check exp
            Log.i("Error2 :",io.getMessage());
        }
        finally
        {
            if (output != null) try { output.close(); } catch (IOException ignoreIO) {Log.i("Error3 :",ignoreIO.getMessage());}
        }


        return response;

    }

    public static String areserve()
    {
        final String SERVER_URL = "http://169.254.46.158:8084/mybus/areserv"; // The server(Servlet) which will process the request. Note 10.0.2.2 is the localhost IP for emulator
        final String CHAR_SET = "UTF-8"; // Encoding used for the parameters

        OutputStream output = null;
        String response = "";

        try
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            Log.i("HTTP :", "Preparing data");
            // ------------------------------------------------------START: PREPARE CONNECTION AND REQUEST ------------------------------- //

            // Prepare the data string  [ firstName=JOHN&lastName=SMITH ]
            String data="";


            URLConnection connection = new URL(SERVER_URL).openConnection(); // Create a connection to server using URL
            connection.setDoOutput(true); // This means POST method to be used
            connection.setRequestProperty("Accept-Charset", CHAR_SET); //For servers to know what encoding is used for the parameters
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=" + CHAR_SET);


            output = null;
            output = connection.getOutputStream(); //open a Output stream from the connection for posting data
            output.write(data.getBytes(CHAR_SET)); //Post data
            output.flush();output.close();
            //------------------------------------------------------ END: PREPARE CONNETION AND REQUEST --------------------------------- //

            Log.i("HTTP :", "sending data");
            InputStream responseStream = connection.getInputStream(); //This is when the request is actually fired



            //------------------------------------------------------ START: READ RESPONSE ------------------------------------------------ //

            Log.i("HTTP :", "Reading response");
            BufferedReader rd = new BufferedReader(new InputStreamReader(responseStream,CHAR_SET)); // Connect a BufferReader to the inputStream
            String line = null;

            while ((line = rd.readLine()) != null) // Read the response line-by-line from the bufferedReader
            {
                response += line;
            }

            //------------------------------------------------------ END: READ RESPONSE ------------------------------------------------- //
            Log.i("response :",response);


        }
        catch (UnsupportedEncodingException e)
        {
            Log.i("Error1 :",e.getMessage());
            e.printStackTrace();

        }
        catch(IOException io)
        {
            //Log and check exp
            Log.i("Error2 :",io.getMessage());
        }
        finally
        {
            if (output != null) try { output.close(); } catch (IOException ignoreIO) {Log.i("Error3 :",ignoreIO.getMessage());}
        }


        return response;

    }

    public static String abus()
    {
        final String SERVER_URL = "http://169.254.46.158:8084/mybus/abus"; // The server(Servlet) which will process the request. Note 10.0.2.2 is the localhost IP for emulator
        final String CHAR_SET = "UTF-8"; // Encoding used for the parameters

        OutputStream output = null;
        String response = "";

        try
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            Log.i("HTTP :", "Preparing data");
            // ------------------------------------------------------START: PREPARE CONNECTION AND REQUEST ------------------------------- //

            // Prepare the data string  [ firstName=JOHN&lastName=SMITH ]
            String data="";


            URLConnection connection = new URL(SERVER_URL).openConnection(); // Create a connection to server using URL
            connection.setDoOutput(true); // This means POST method to be used
            connection.setRequestProperty("Accept-Charset", CHAR_SET); //For servers to know what encoding is used for the parameters
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=" + CHAR_SET);


            output = null;
            output = connection.getOutputStream(); //open a Output stream from the connection for posting data
            output.write(data.getBytes(CHAR_SET)); //Post data
            output.flush();output.close();
            //------------------------------------------------------ END: PREPARE CONNETION AND REQUEST --------------------------------- //

            Log.i("HTTP :", "sending data");
            InputStream responseStream = connection.getInputStream(); //This is when the request is actually fired



            //------------------------------------------------------ START: READ RESPONSE ------------------------------------------------ //

            Log.i("HTTP :", "Reading response");
            BufferedReader rd = new BufferedReader(new InputStreamReader(responseStream,CHAR_SET)); // Connect a BufferReader to the inputStream
            String line = null;

            while ((line = rd.readLine()) != null) // Read the response line-by-line from the bufferedReader
            {
                response += line;
            }

            //------------------------------------------------------ END: READ RESPONSE ------------------------------------------------- //
            Log.i("response :",response);


        }
        catch (UnsupportedEncodingException e)
        {
            Log.i("Error1 :",e.getMessage());
            e.printStackTrace();

        }
        catch(IOException io)
        {
            //Log and check exp
            Log.i("Error2 :",io.getMessage());
        }
        finally
        {
            if (output != null) try { output.close(); } catch (IOException ignoreIO) {Log.i("Error3 :",ignoreIO.getMessage());}
        }


        return response;

    }

    public static String cancel(String cname,String bid, String doj, String fare)
    {
        final String SERVER_URL = "http://169.254.46.158:8084/mybus/cancel"; // The server(Servlet) which will process the request. Note 10.0.2.2 is the localhost IP for emulator
        final String CHAR_SET = "UTF-8"; // Encoding used for the parameters

        OutputStream output = null;
        String response = "";

        try
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            Log.i("HTTP :", "Preparing data");
            // ------------------------------------------------------START: PREPARE CONNECTION AND REQUEST ------------------------------- //

            // Prepare the data string  [ firstName=JOHN&lastName=SMITH ]
            String data=URLEncoder.encode("cname", CHAR_SET) + "=" + URLEncoder.encode(cname, CHAR_SET);
            data += "&" + URLEncoder.encode("bid", CHAR_SET) + "=" + URLEncoder.encode(bid, CHAR_SET);
            data += "&" + URLEncoder.encode("doj", CHAR_SET) + "=" + URLEncoder.encode(doj, CHAR_SET);
            data += "&" + URLEncoder.encode("fare", CHAR_SET) + "=" + URLEncoder.encode(fare, CHAR_SET);

            URLConnection connection = new URL(SERVER_URL).openConnection(); // Create a connection to server using URL
            connection.setDoOutput(true); // This means POST method to be used
            connection.setRequestProperty("Accept-Charset", CHAR_SET); //For servers to know what encoding is used for the parameters
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=" + CHAR_SET);


            output = null;
            output = connection.getOutputStream(); //open a Output stream from the connection for posting data
            output.write(data.getBytes(CHAR_SET)); //Post data
            output.flush();output.close();
            //------------------------------------------------------ END: PREPARE CONNETION AND REQUEST --------------------------------- //

            Log.i("HTTP :", "sending data");
            InputStream responseStream = connection.getInputStream(); //This is when the request is actually fired



            //------------------------------------------------------ START: READ RESPONSE ------------------------------------------------ //

            Log.i("HTTP :", "Reading response");
            BufferedReader rd = new BufferedReader(new InputStreamReader(responseStream,CHAR_SET)); // Connect a BufferReader to the inputStream
            String line = null;

            while ((line = rd.readLine()) != null) // Read the response line-by-line from the bufferedReader
            {
                response += line;
            }

            //------------------------------------------------------ END: READ RESPONSE ------------------------------------------------- //
            Log.i("response :",response);


        }
        catch (UnsupportedEncodingException e)
        {
            Log.i("Error1 :",e.getMessage());
            e.printStackTrace();

        }
        catch(IOException io)
        {
            //Log and check exp
            Log.i("Error2 :",io.getMessage());
        }
        finally
        {
            if (output != null) try { output.close(); } catch (IOException ignoreIO) {Log.i("Error3 :",ignoreIO.getMessage());}
        }


        return response;

    }

}
