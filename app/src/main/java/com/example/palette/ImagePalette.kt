package com.example.palette

import android.graphics.drawable.BitmapDrawable
import android.os.Build
import android.os.Bundle
import android.transition.ChangeImageTransform
import android.transition.Fade
import android.transition.Slide
import android.view.Window
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.palette.graphics.Palette

class ImagePalette : AppCompatActivity() {
    // Por algún motivo hay que usar esto para que funcione el Palette al coger los colores
    @RequiresApi(Build.VERSION_CODES.M)

    override fun onCreate(savedInstanceState: Bundle?) {
        window.requestFeature(Window.FEATURE_CONTENT_TRANSITIONS)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_palette)

        window.enterTransition = Fade()
        window.exitTransition = Slide()
        window.sharedElementEnterTransition = ChangeImageTransform()

        val toolbar = findViewById<Toolbar>(R.id.appbar)
        setSupportActionBar(toolbar)

        val lightVibrant = findViewById<TextView>(R.id.lightVibrant)
        val muted = findViewById<TextView>(R.id.muted)
        val darkMuted = findViewById<TextView>(R.id.darkMuted)
        val lightMuted = findViewById<TextView>(R.id.lightMuted)


        // Obtén la imagen seleccionada del Intent
        val selectedImage = intent.getIntExtra("image_resource", 0)

        // Configura la imagen en el ImageView
        val imageView: ImageView = findViewById(R.id.imageView)
        imageView.setImageResource(selectedImage)

        val bitmap = (imageView.drawable as? BitmapDrawable)?.bitmap ?: return

        Palette.from(bitmap).generate { palette ->
            val vibrantColor = palette?.getVibrantColor(getColor(android.R.color.black)) ?: getColor(android.R.color.black)
            toolbar.setBackgroundColor(vibrantColor)

            lightVibrant.setBackgroundColor(palette?.getLightVibrantColor(getColor(android.R.color.black))?: getColor(android.R.color.black))
            muted.setBackgroundColor(palette?.getMutedColor(getColor(android.R.color.black))?: getColor(android.R.color.black))
            darkMuted.setBackgroundColor(palette?.getDarkMutedColor(getColor(android.R.color.black))?: getColor(android.R.color.black))
            lightMuted.setBackgroundColor(palette?.getLightMutedColor(getColor(android.R.color.black))?: getColor(android.R.color.black))
        }

    }
}
