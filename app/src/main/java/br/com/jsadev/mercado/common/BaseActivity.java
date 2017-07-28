package br.com.jsadev.mercado.common;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import br.com.jsadev.mercado.CategoriaActivity;
import br.com.jsadev.mercado.ListaActivity;
import br.com.jsadev.mercado.MainActivity;
import br.com.jsadev.mercado.ProdutoActivity;
import br.com.jsadev.mercado.R;
import br.com.jsadev.mercado.UnidadeActivity;

/**
 * Created by janailson on 25/07/2017.
 */

public abstract class BaseActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    public Drawer.Result result;
    private int mPositionClicked = -1;

    public String actionBarName() {
        return getString(R.string.app_name);
    }

    public boolean isChildView() {
        return false;
    }

    protected void setPositionClicked(int n) {
        mPositionClicked = n;
    }

    protected void setupNavigation(Bundle savedInstanceState) {
        mToolbar = (Toolbar) findViewById(R.id.tb_main);
        mToolbar.setTitle(actionBarName());
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(isChildView());
        getSupportActionBar().setHomeButtonEnabled(isChildView());

        result = new Drawer()
                .withActivity(this)
                .withToolbar(mToolbar)
                .withDisplayBelowToolbar(true)
                .withActionBarDrawerToggleAnimated(true)
                .withDrawerGravity(Gravity.LEFT)
                .withSavedInstance(savedInstanceState)
                .withSelectedItem(mPositionClicked)
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id, IDrawerItem drawerItem) {
                        for (int i = 0; i < result.getDrawerItems().size(); i++) {
                            if (result.getDrawerItems().get(i).getType() == "PRIMARY_ITEM") {
                                PrimaryDrawerItem item = (PrimaryDrawerItem) result.getDrawerItems().get(i);
                                item.setIcon(getResources().getDrawable(getCorretcDrawerIcon(i, false)));
                            }
                        }

                        if (result.getDrawerItems().get(position).getType() == "PRIMARY_ITEM") {
                            ((PrimaryDrawerItem) drawerItem).setIcon(getResources().getDrawable(getCorretcDrawerIcon(position, true)));
                            result.getAdapter().notifyDataSetChanged();

                            switch (position) {
                                // HOME
                                case 0:
                                    if (actionBarName() == DrawerMenu.HOME.getNome()) {
                                        reloadActivity();
                                    } else {
                                        Intent intent = new Intent(getBaseContext(), MainActivity.class);
                                        startActivity(intent);
                                    }
                                    break;
                                // LISTAS
                                case 2:
                                    if (actionBarName() == DrawerMenu.LISTAS.getNome()) {
                                        reloadActivity();
                                    } else {
                                        Intent intent = new Intent(getBaseContext(), ListaActivity.class);
                                        startActivity(intent);
                                    }
                                    break;
                                // PRODUTOS
                                case 3:
                                    if (actionBarName() == DrawerMenu.PRODUTOS.getNome()) {
                                        reloadActivity();
                                    } else {
                                        Intent intent = new Intent(getBaseContext(), ProdutoActivity.class);
                                        startActivity(intent);
                                    }
                                    break;
                                // CATEGORIAS
                                case 4:
                                    if (actionBarName() == DrawerMenu.CATEGORIAS.getNome()) {
                                        reloadActivity();
                                    } else {
                                        Intent intent = new Intent(getBaseContext(), CategoriaActivity.class);
                                        startActivity(intent);
                                    }
                                    break;
                                // UNIDADES DE MEDIDA
                                case 5:
                                    if (actionBarName() == DrawerMenu.UNIDADES.getNome()) {
                                        reloadActivity();
                                    } else {
                                        Intent intent = new Intent(getBaseContext(), UnidadeActivity.class);
                                        startActivity(intent);
                                    }
                                    break;
                            }
                        }
                    }
                })
                .build();

        result.addItem(new PrimaryDrawerItem().withName(DrawerMenu.HOME.getNome()).withIcon(getResources().getDrawable(getCorretcDrawerIcon(DrawerMenu.HOME.getPosition(), mPositionClicked == DrawerMenu.HOME.getPosition()))));
        result.addItem(new DividerDrawerItem());
        result.addItem(new PrimaryDrawerItem().withName(DrawerMenu.LISTAS.getNome()).withIcon(getResources().getDrawable(getCorretcDrawerIcon(DrawerMenu.LISTAS.getPosition(), mPositionClicked == DrawerMenu.LISTAS.getPosition()))));
        result.addItem(new PrimaryDrawerItem().withName(DrawerMenu.PRODUTOS.getNome()).withIcon(getResources().getDrawable(getCorretcDrawerIcon(DrawerMenu.PRODUTOS.getPosition(), mPositionClicked == DrawerMenu.PRODUTOS.getPosition()))));
        result.addItem(new PrimaryDrawerItem().withName(DrawerMenu.CATEGORIAS.getNome()).withIcon(getResources().getDrawable(getCorretcDrawerIcon(DrawerMenu.CATEGORIAS.getPosition(), mPositionClicked == DrawerMenu.CATEGORIAS.getPosition()))));
        result.addItem(new PrimaryDrawerItem().withName(DrawerMenu.UNIDADES.getNome()).withIcon(getResources().getDrawable(getCorretcDrawerIcon(DrawerMenu.UNIDADES.getPosition(), mPositionClicked == DrawerMenu.UNIDADES.getPosition()))));

        result.getActionBarDrawerToggle().setDrawerIndicatorEnabled(!isChildView());
    }

    private int getCorretcDrawerIcon(int position, boolean isSelected) {
        if (DrawerMenu.HOME.isPosition(position))
            return (isSelected ? R.drawable.ic_home_selected : R.drawable.ic_home);
        else if (DrawerMenu.LISTAS.isPosition(position))
            return (isSelected ? R.drawable.ic_lista_selected : R.drawable.ic_lista);
        else if (DrawerMenu.PRODUTOS.isPosition(position))
            return (isSelected ? R.drawable.ic_produto_selected : R.drawable.ic_produto);
        else if (DrawerMenu.CATEGORIAS.isPosition(position))
            return (isSelected ? R.drawable.ic_categoria_selected : R.drawable.ic_categoria);
        else if (DrawerMenu.UNIDADES.isPosition(position))
            return (isSelected ? R.drawable.ic_unidade_selected : R.drawable.ic_unidade);
        else
            return 0;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        if (result != null && result.isDrawerOpen())
            result.closeDrawer();
        else
            super.onBackPressed();
    }

    public abstract void reloadActivity();

    protected void makeToast(String text) {
        Toast.makeText(getBaseContext(), text, Toast.LENGTH_LONG).show();
    }
}
