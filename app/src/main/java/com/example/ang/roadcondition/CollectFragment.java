package com.example.ang.roadcondition;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CollectFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class CollectFragment extends ListFragment {

    private ArrayList<String> collects;
    private OnFragmentInteractionListener mListener;

    public CollectFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle bundle = this.getArguments();
        collects = bundle.getStringArrayList("collects");
        ArrayAdapter<String> list = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, collects);
        setListAdapter(list);

        return inflater.inflate(R.layout.fragment_collect, container, false);
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void onListItemClick(ListView l, View v, int position, long id) {
        // get query string and create a URL representing the search
        final String collect = collects.get(position);
        final String infor = CollectActivity.savedRoute.getString(collect, "");

        mListener.sendToShowDetail(collect,infor);
    }; // end itemClickListener declaration*/

    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);

        Button button = (Button) getActivity().findViewById(R.id.addCollect);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.clickButton();
            }
        });

        AdapterView.OnItemLongClickListener listener = new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int position, long id) {
                final String collect = collects.get(position);

                // create a new AlertDialog
                AlertDialog.Builder builder =
                        new AlertDialog.Builder(CollectFragment.this.getActivity());

                // set list of items to display in dialog
                builder.setItems(R.array.dialog_items,
                        new DialogInterface.OnClickListener() {
                            // responds to user touch by sharing, editing or
                            // deleting a saved search
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                mListener.deleteCollect(collect);
                            }
                        } // end DialogInterface.OnClickListener
                ); // end call to builder.setItems

                builder.create().show(); // display the AlertDialog
                return true;
            }
        };

        getListView().setOnItemLongClickListener(listener);

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void sendToShowDetail(String collect,String infor);
        public void deleteCollect(String collect);
        public void clickButton();
    }

}
