package com.example.glaturski.sortingalgorithms;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import java.lang.reflect.Array;

public class MainActivity extends AppCompatActivity {

    private static Contact[] contactsArray;

    private static int numContactsAdded;

    private EditText mNameInput;
    private EditText mPhoneInput;
    private EditText mEmailInput;

    private TextView mErrorMessage;
    private TextView mSortedList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mNameInput = (EditText) findViewById(R.id.et_name);
        mPhoneInput = (EditText) findViewById(R.id.et_phone);
        mEmailInput = (EditText) findViewById(R.id.et_email);

        mErrorMessage = (TextView) findViewById(R.id.tv_error);
        mSortedList = (TextView) findViewById(R.id.tv_list);


        contactsArray = new Contact[50];

        numContactsAdded = 0;


    }

    public void addContact(View view)
    {
        InputMethodManager inputMethodManager = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);

        Contact toAdd;

        if (mNameInput.getText().length() == 0)
        {
            mErrorMessage.setText("You must enter a least a one to add a contact");
        }
        else if (numContactsAdded >= contactsArray.length)
        {
            mErrorMessage.setText("You cannot enter any more contacts");
        }
        else
        {
            toAdd = new Contact( mNameInput.getText().toString(),mPhoneInput.getText().toString(),mEmailInput.getText().toString());

            contactsArray[numContactsAdded] = toAdd;
            numContactsAdded++;
            mNameInput.setText("");
            mPhoneInput.setText("");
            mEmailInput.setText("");

            mNameInput.requestFocus();

            if (inputMethodManager != null)
            {
                inputMethodManager.showSoftInput(mNameInput,InputMethodManager.SHOW_IMPLICIT);
            }

            mErrorMessage.setText("Contact added sucsesfully");

        }
    }

    public void sortContacts(View view)
    {
        String storedLIst = "";

        insertionSort();
        //selectionSort();
        //quickSort(contactsArray,0,numContactsAdded -1);

        for (int i = 0; i < numContactsAdded; i++)
        {
            if (contactsArray[i] != null)
            {
                storedLIst += String.format("Name: %20s \nPhone: %20s \nEmail: %20s\n\n",contactsArray[i].getName(), contactsArray[i].getPhone(),contactsArray[i].getEmail() );
            }

        }

        mErrorMessage.setText("");
        mSortedList.setText(storedLIst);
    }

    /**
     * insertionSort uses the insertion sort algorithm to sort a list of items in ascending order
     *
     * @param "" there are no paramiters
     * @return "" Nothing is returned
     */
    private void insertionSort()
    {
        //key may have to be a different data type...
        Contact key;
        int index;


        //this is where insertionSort starts
        for (int j =0; j < numContactsAdded; j++)
        {
            key = contactsArray[j];
            index = j-1;

            while( index >= 0 && contactsArray[index].getName().compareTo(key.getName()) > 0)
            {
                contactsArray[index+1] =contactsArray[index];
                index = index - 1;
            }

            contactsArray[index+1] = key;
        }

    }

    /**
     * selectionSort uses the selection sort algorithm to sort a list of items in ascending order
     *
     * @param "" there are no paramiters
     * @return a String that will display the sorted list and how many steps it took
     */
    private void selectionSort()
    {
        int minIndex;
        Contact toSwap;

        for (int j = 0; j < numContactsAdded - 1; j++)
        {
            minIndex = j;

            for (int k = j+1; k < numContactsAdded; k++)
            {
                if (contactsArray[k].getName().compareTo(contactsArray[minIndex].getName()) < 0 )
                {
                    minIndex = k;
                }

            }

            toSwap = contactsArray[minIndex];
            contactsArray[minIndex] = contactsArray[j];
            contactsArray[j] = toSwap;
        }
    }

    /**
     * quickSort uses the quick sort algorithm to sort a list in acsending order
     * @param array is the array we are storing
     * @param low is the beginning index of the section of the array we would like to sort
     * @param high is the ending index of the selection of the array we would like to sort
     * @return Nothing is returned
     */
    private void quickSort( Contact[] array, int low, int high)
    {
        int middle;
        Contact pivot;

        int i;
        int j;

        Contact toSwap;

        if( low < high )
        {
            middle = low + (high - low) / 2;
            pivot = array[middle];

            i =low;
            j = high;

            while( i<= j )
            {
                while(array[i].getName().compareTo(pivot.getName()) < 0)
                {
                    i++;


                }

                while (array[j].getName().compareTo(pivot.getName()) > 0)
                {
                    j--;


                }

                if (i <= j )
                {
                    toSwap = array[i];
                    array[i] = array[j];
                    array[j] = toSwap;
                    i++;
                    j--;

                }


            }

            if (low < j)
            {
                quickSort(array, low, j );


            }
            if (high > i)
            {
                quickSort(array, high, j);


            }
        }


    }

}
