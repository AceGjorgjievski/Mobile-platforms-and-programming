package com.example.lab1mpip

import android.content.Intent
import android.content.pm.ResolveInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class ImplicitActivity : AppCompatActivity() {

    private lateinit var txtUserChoice: TextView;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_implicit)

        txtUserChoice = findViewById(R.id.txtUserChoice)

        val activityNames = getActivities().map { getActivityName(it) }
        txtUserChoice.text = activityNames.joinToString("\n")
    }

    private fun getActivities(): List<ResolveInfo> {
        val intent = Intent(Intent.ACTION_MAIN, null)
        intent.addCategory(Intent.CATEGORY_LAUNCHER)

        return packageManager.queryIntentActivities(intent, 0)
    }

    private fun getActivityName(resolveInfo: ResolveInfo): String {
        val packageName = resolveInfo.activityInfo.packageName
        val className = resolveInfo.activityInfo.name
        return className.removePrefix("$packageName.")
    }
}
