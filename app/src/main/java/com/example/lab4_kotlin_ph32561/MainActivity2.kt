package com.example.lab4_kotlin_ph32561

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var selectedArticle by remember { mutableStateOf(1) }

            Column {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    CustomButton(text = "Bài 1", onClick = { selectedArticle = 1 })
                    CustomButton(text = "Bài 2", onClick = { selectedArticle = 2 })
                    CustomButton(text = "Bài 3", onClick = { selectedArticle = 3 })
                }

                when (selectedArticle) {
                    1 -> ArticleOne()
                    2 -> LoginScreen()
                    3 -> ArticleThree()
                }
            }
        }
    }

    @Composable
    fun ArticleOne() {
        val images = listOf(
            R.drawable.item_1,
            R.drawable.item_2,
            R.drawable.item_3,
            R.drawable.item_4,
            R.drawable.item_4_1,

            )
        Column {
            Icon(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = "Logo",
                modifier = Modifier.padding(vertical = 5.dp, horizontal = 200.dp)
            )
            HozrizontalImageList(imageList = images)
            VerticalImageList(imageList = images)
        }
    }

    @Composable
    fun ArticleThree() {
        Scaffold(
            floatingActionButton = {
                FloatingActionButton(onClick = { /*TODO*/ }) {
                    Icon(Icons.Filled.Add, contentDescription = "Add")
                }
            }
        ) { innerPadding ->
            NoteApp(innerPadding)
        }
    }

    @Composable
    fun LoginScreen() {
        val context = LocalContext.current
        var username by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = "Logo",
                modifier = Modifier
                    .size(100.dp)
                    .padding(8.dp)
            )
            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(
                value = username,
                onValueChange = { username = it },
                label = { Text(text = "Username") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text(text = "Password") },
                visualTransformation = PasswordVisualTransformation(),
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(20.dp))

            CustomButton(
                text = "Login",
                onClick = {
                    if (username.isNotBlank() && password.isNotBlank()) {
                        Toast.makeText(context, "Login successful", Toast.LENGTH_LONG).show()
                    } else {
                        Toast.makeText(context, "Please enter username and password", Toast.LENGTH_LONG).show()
                    }
                }
            )
        }
    }

    @Composable
    fun HozrizontalImageList(imageList: List<Int>) {
        val scrollState = rememberScrollState()
        Row(
            modifier = Modifier
                .horizontalScroll(scrollState)
                .padding(16.dp)
        ) {
            imageList.forEachIndexed { index, image ->
                Image(
                    painter = painterResource(id = image),
                    contentDescription = "Image Description",
                    contentScale = ContentScale.FillHeight,
                    modifier = Modifier
                        .size(200.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .padding(start = if (index == 0) 0.dp else 8.dp, end = 8.dp)
                )
            }
        }
    }

    @Composable
    fun VerticalImageList(imageList: List<Int>) {
        val scrollState = rememberScrollState()
        Column(
            modifier = Modifier
                .verticalScroll(scrollState)
                .padding(16.dp)
        ) {
            imageList.forEachIndexed { index, image ->
                Image(
                    painter = painterResource(id = image),
                    contentDescription = "Image Description",
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .padding(top = if (index == 0) 0.dp else 8.dp, bottom = 8.dp)
                        .fillMaxWidth()

                )
            }
        }
    }

    @Composable
    fun NoteApp(paddingValues: PaddingValues) {
        val notes = listOf("Note 1", "Note 2", "Note 3", "Note 4", "Note 5")
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(8.dp)
        ) {
            notes.forEach { note ->
                NoteCard(noteText = note)
            }
        }
    }

    @Composable
    fun NoteCard(noteText: String) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .background(color = Color.LightGray, shape = MaterialTheme.shapes.medium)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = noteText,
                    modifier = Modifier
                        .weight(1f)
                        .padding(16.dp),
                    style = MaterialTheme.typography.bodyLarge
                )
                Icon(
                    imageVector = Icons.Filled.KeyboardArrowDown,
                    contentDescription = "Expand Note",
                    modifier = Modifier
                        .padding(16.dp)
                        .align(Alignment.CenterVertically)
                )
            }
        }
    }

    @Composable
    fun CustomButton(
        text: String,
        onClick: () -> Unit,
        modifier: Modifier = Modifier,
        colors: ButtonColors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = Color.White
        ),
        shape: RoundedCornerShape = RoundedCornerShape(12.dp)
    ) {
        Button(
            onClick = onClick,
            colors = colors,
            shape = shape,
            modifier = modifier
                .width(100.dp)
                .height(50.dp)
                .clip(shape)
        ) {
            Text(text, fontSize = 18.sp)
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        HozrizontalImageList(
            listOf(
                R.drawable.ic_launcher_background,
                R.drawable.ic_launcher_background,
                R.drawable.ic_launcher_background,
                R.drawable.ic_launcher_background
            )
        )
    }

    @Preview(showBackground = true)
    @Composable
    fun PreviewVerticalImageList() {
        VerticalImageList(
            listOf(
                R.drawable.ic_launcher_background,
                R.drawable.ic_launcher_background,
                R.drawable.ic_launcher_background,
                R.drawable.ic_launcher_background
            )
        )
    }

    @Preview(showBackground = true)
    @Composable
    fun PreviewLoginScreen() {
        LoginScreen()
    }

    @Preview(showBackground = true)
    @Composable
    fun PreviewArticleOne() {
        ArticleOne()
    }

    @Preview(showBackground = true)
    @Composable
    fun PreviewArticleThree() {
        ArticleThree()
    }
}
