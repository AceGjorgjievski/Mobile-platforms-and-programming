package com.example.lab1mpip

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import com.example.lab1mpip.viewmodel.ShowTextViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var btnImplicitActivity : Button;
    private lateinit var btnExplicitActivity : Button;
    private lateinit var btnImage : Button;

    private lateinit var textViewModel : ShowTextViewModel;

    private lateinit var txtResult : TextView;

    private lateinit var imageResultLauncher: ActivityResultLauncher<Intent>

    var resultLauncer =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if(result.resultCode == RESULT_OK) {
                val data: Intent? = result.data;
                val userChoice: String? = data?.getStringExtra("userChoice")
                this.txtResult.text = userChoice?: "No Choice"
                println(data?.getStringExtra("userChoice"))
            }
        }


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        btnImplicitActivity = findViewById(R.id.btnImplicitActivity);
        btnExplicitActivity = findViewById(R.id.btnExplicitActivity);
        btnImage = findViewById(R.id.btnImage);

        txtResult = findViewById(R.id.txtResult);

        textViewModel = ViewModelProvider(this)[ShowTextViewModel::class.java]

        textViewModel.getUserChoiceValue().observe(this) {
            txtResult.text = textViewModel.getUserChoice();
        }

        var bundle: Bundle? = intent.extras
        val choice: String? = bundle?.getString("userChoice") ?: "None"
        this.txtResult.text = choice.toString();

        imageResultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if(result.resultCode == RESULT_OK) {
                    val data: Intent? = result.data;
                    val selectedImageUri: Uri? = data?.data;
//                    this.displayImage(selectedImageUri);
                    this.displayImageForGooglePhotos(selectedImageUri);
                }

            }

        btnExplicitActivity.setOnClickListener {
            Intent(this, ExplicitActivity::class.java).let { i ->
                i.putExtra("userChoice", textViewModel.getUserChoice())
                resultLauncer.launch(i)
            }
        }

        btnImplicitActivity.setOnClickListener {
//            startActivity(Intent(this, ImplicitActivity::class.java))
//            this.launchImplicitIntent()
            Intent(Intent.ACTION_SEND).apply {
                action = "mk.ukim.finki.lab_intents.IMPLICIT_ACTION"
                type = "text/plain"
            }.let { i ->
                startActivity(i)
            }
        }

        btnImage.setOnClickListener {
            this.pickImage()
        }


    }

    private fun launchImplicitIntent() {
//        val implicitIntent = Intent("mk.ukim.finki.mpip.IMPLICIT_ACTION")
//        implicitIntent.type = "text/plain"
//
//        val activities = packageManager.queryIntentActivities(implicitIntent, 0)
//        val classNames = activities.map { it.activityInfo.name }
//
//        txtResult.text = classNames.joinToString("\n")
//
//        val intent = Intent(this, ImplicitActivity::class.java)
//        intent.putExtra("classNames", classNames.toTypedArray())
//
//        startActivity(intent)
        Intent("mk.ukim.finki.lab_intents.IMPLICIT_ACTION").apply {
            type = "text/plain"
        }.let { i ->
            startActivity(i)
        }
    }

    private fun pickImage() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        imageResultLauncher.launch(intent)
    }

    /**
     * with this method does not work if you want to
     * open an image from Google Photos
     */
    private fun displayImage(selectedImageUri: Uri?) {
        val viewImageIntent = Intent(Intent.ACTION_VIEW)
        viewImageIntent.setDataAndType(selectedImageUri, "image/*")
        viewImageIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        startActivity(viewImageIntent)
    }

    private fun displayImageForGooglePhotos(selectedImageUri: Uri?) {
        selectedImageUri?.let { uri ->
            val projection = arrayOf(
                MediaStore.Images.Media._ID,
                MediaStore.Images.Media.DATA
            )
            val cursor = contentResolver.query(uri, projection, null, null, null)

            cursor?.use {
                if (it.moveToFirst()) {
                    val idColumnIndex = it.getColumnIndex(MediaStore.Images.Media._ID)
                    val dataColumnIndex = it.getColumnIndex(MediaStore.Images.Media.DATA)

                    if (idColumnIndex != -1 && dataColumnIndex != -1) {
                        val id = it.getLong(idColumnIndex)
                        val contentUri = Uri.withAppendedPath(
                            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                            id.toString()
                        )

                        val viewImageIntent = Intent(Intent.ACTION_VIEW)
                        viewImageIntent.setDataAndType(contentUri, "image/*")
                        viewImageIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)

                        startActivity(viewImageIntent)
                    }
                }
            }
        }
    }

}