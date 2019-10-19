package com.m35725.unisc_pdm;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

public class CustomExpandableListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> expandableListTitle;
    private HashMap<String, List<String>> expandableListDetail;
    private List<HashMap<String, String>> listaOperacoes;

    public CustomExpandableListAdapter(Context context,
                                       List<String> expandableListTitle,
                                       HashMap<String, List<String>> expandableListDetail,
                                       List<HashMap<String, String>> listaOperacoes ) {
        this.context = context;
        this.expandableListTitle = expandableListTitle;
        this.expandableListDetail = expandableListDetail;
        this.listaOperacoes = listaOperacoes;
    }

    @Override
    public Object getChild(int listPosition, int expandedListPosition) {
        return this.expandableListDetail.get(this.expandableListTitle.get(listPosition))
                .get(expandedListPosition);
    }

    @Override
    public long getChildId(int listPosition, int expandedListPosition) {
        return expandedListPosition;
    }

    @Override
    public View getChildView(int listPosition, final int expandedListPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.desafio_expandable_list_item, null);
        }
        HashMap<String, String> item= listaOperacoes.get(listPosition);
        if ( item != null ) {
            if (item.containsKey("operador1")) {
                TextView txtViewOp1= (TextView) convertView.findViewById(R.id.txtViewDesafio_Operador1);
                txtViewOp1.setText(item.get("operador1").toString());
            }
            if (item.containsKey("operador2")) {
                TextView txtViewOp2= (TextView) convertView.findViewById(R.id.txtViewDesafio_Operador2);
                txtViewOp2.setText(item.get("operador2").toString());
            }
            if (item.containsKey("operacao")) {
                TextView txtViewOper= (TextView) convertView.findViewById(R.id.txtViewDesafio_Operacao);
                if( item.get("operacao").toString().equals("+") )
                    txtViewOper.setText("soma");
                if( item.get("operacao").toString().equals("-") )
                    txtViewOper.setText("subtração");
                if( item.get("operacao").toString().equals("*") )
                    txtViewOper.setText("multiplicação");
                if( item.get("operacao").toString().equals("/") )
                    txtViewOper.setText("divisão");
            }
        }
        return convertView;
    }

    @Override
    public int getChildrenCount(int listPosition) {
        return this.expandableListDetail.get(this.expandableListTitle.get(listPosition))
                .size();
    }

    @Override
    public Object getGroup(int listPosition) {
        return this.expandableListTitle.get(listPosition);
    }

    @Override
    public int getGroupCount() {
        return this.expandableListTitle.size();
    }

    @Override
    public long getGroupId(int listPosition) {
        return listPosition;
    }

    @Override
    public View getGroupView(int listPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.desafio_expandable_list_grupo, null);
        }
        HashMap<String, String> item= listaOperacoes.get(listPosition);
        if ( item != null ) {
            if (item.containsKey("dataHora")) {
                TextView txtViewDataHora= (TextView) convertView.findViewById(R.id.txtViewDesafio_DataHora);
                txtViewDataHora.setText(item.get("dataHora").toString());
            }
            if (item.containsKey("resultado")) {
                TextView txtViewResultado= (TextView) convertView.findViewById(R.id.txtViewDesafio_Resultado);
                txtViewResultado.setText(item.get("resultado").toString());
            }
        }
        //Icone
        if( isExpanded ){
            ImageView imgViewIcone= (ImageView) convertView.findViewById(R.id.imgViewDesafio_Icone);
            //imgViewIcone.setImageDrawable(R.drawable.seta_para_baixo);
            imgViewIcone.setImageResource(R.drawable.seta_para_baixo);
        }else{
            ImageView imgViewIcone= (ImageView) convertView.findViewById(R.id.imgViewDesafio_Icone);
            //imgViewIcone.setImageDrawable(R.drawable.seta_para_cima);
            imgViewIcone.setImageResource(R.drawable.seta_para_cima);
        }
        //Retorno
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int listPosition, int expandedListPosition) {
        return true;
    }

}