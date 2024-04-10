import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalResourceApi::class)
@Composable
@Preview
fun App() {
    MaterialTheme {
        var counter by remember { mutableStateOf(0) }
        var running by remember { mutableStateOf(false) }
        var originalCounter by remember { mutableStateOf(0) }
        var progress by remember { mutableStateOf(0.0F) }

        LaunchedEffect(running) {
            while (running && counter > 0 && originalCounter > 0) {
                delay(1000) // Delay for 1 second
                counter-- // Decrease the counter by 1
                progress = (counter.toFloat() / originalCounter.toFloat())
                //println("Progress=$progress" + "ogCounter= ${originalCounter} + Counter= ${counter}")
            }
        }
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator(
                progress = progress,
                modifier = Modifier.size(250.dp)
            )
            Text("${counter / 60}m + ${counter % 60}s", style = MaterialTheme.typography.h6)
        }
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.Bottom
            ) {
                Button(onClick = { counter += 60 }) {
                    Text("+ 1m")
                }
                Spacer(modifier = Modifier.width(16.dp))
                Button(onClick = { counter++ }) {
                    Text("+ 1s")
                }
            }
        }
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.Start
        ) {
            Button(onClick = { running = !running; originalCounter = counter; }) {
                Text(if (running) "Pause " + "Timer" else "Start " + "Timer")
            }
        }
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.End
        ) {
            Button(onClick = { if (counter > 0) counter = 0; running = false; progress = 100.0F }) {
                Text("Reset")
            }
        }
    }
}
