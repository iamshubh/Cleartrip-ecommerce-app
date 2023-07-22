package com.cleartrip.ecommerce

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.cleartrip.ecommerce.model.ProductsItem
import com.cleartrip.ecommerce.ui.theme.EcommerceTheme

class MainActivity : ComponentActivity() {

    val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EcommerceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainApp(viewModel)
                }
            }
        }
        initialize()
        observers()
    }

    private fun initialize() {
//        viewModel.loadProds()
    }


    fun observers() {
        viewModel.products.observe(this) {

        }
    }

    override fun onStart() {
        super.onStart()
    }
}

@Composable
fun HomeScreen(
    viewModel: MainViewModel,
    onAdd: (ProductsItem) -> Unit,
    modifier: Modifier = Modifier
) {
//    val response = rememberSaveable { viewModel.products.value }

    val products = listOf(
        ProductsItem(title = "Title1", rating = 4, description = "", price = 100),
        ProductsItem(title = "Title 2", rating = 4, description = "", price = 100),
        ProductsItem(
            title = "Title 3", rating = 2, description = "", price = 100
        )
    )
    products?.let {
        LazyColumn(modifier = modifier, contentPadding = PaddingValues(4.dp)) {
            items(items = products) { it ->
                Item(it, onAdd = { onAdd(it) })
            }
        }
    }
}

@Composable
fun Item(item: ProductsItem, onAdd: () -> Unit, modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .padding(4.dp)
    ) {
        Row() {
            AsyncImage(
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(8.dp)),
                model = item.thumbnail,
                contentDescription = ""
            )
        }
        Column() {
            Text(text = item.title)
            Text(text = item.description, style = MaterialTheme.typography.bodySmall)
            Text(text = item.price.toString(), style = MaterialTheme.typography.bodySmall)
            Text(text = item.rating.toString(), style = MaterialTheme.typography.labelSmall)
            Button(onClick = onAdd) {
                Text(text = "Add")
            }

        }
    }
}

@Composable
fun MainApp(viewModel: MainViewModel) {
    val cartItems: MutableList<ProductsItem> by remember {
        mutableStateOf(mutableListOf())
    }

    val onCheckOut = { /* Todo*/ }

    val onAdd: (ProductsItem) -> Unit = { p: ProductsItem -> cartItems.add(p) }

    HomeScreen(viewModel, onAdd = onAdd)
    ShoppingCart(
        data = cartItems,
        onCheckOut = onCheckOut,
        modifier = Modifier.padding(top = 24.dp)
    )
}

@Composable
fun ShoppingCart(data: List<ProductsItem>, onCheckOut: () -> Unit, modifier: Modifier) {
    var total = 0

    data.forEach { item -> total += item.price }

    Row(modifier = modifier.fillMaxWidth()) {
        Text(text = "Total price")
        Spacer(modifier = Modifier.size(12.dp))
        Text(text = total.toString())

        Button(onClick = onCheckOut) {
            Text(text = "Checkout")
        }
    }

}