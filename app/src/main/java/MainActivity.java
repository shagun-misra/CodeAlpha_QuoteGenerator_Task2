package com.example.codealpha_randomquotegenerator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView tvQuoteText, tvQuoteAuthor;
    private Button btnNewQuote, btnShareQuote;

    private List<Quote> quoteList;
    private Random random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvQuoteText = findViewById(R.id.tvQuoteText);
        tvQuoteAuthor = findViewById(R.id.tvQuoteAuthor);
        btnNewQuote = findViewById(R.id.btnNewQuote);
        btnShareQuote = findViewById(R.id.btnShareQuote);

        random = new Random();
        quoteList = new ArrayList<>();

        // Curated Quote Collection
        quoteList.add(new Quote("The only way to do great work is to love what you do.", "Steve Jobs"));
        quoteList.add(new Quote("Life is what happens when you're busy making other plans.", "John Lennon"));
        quoteList.add(new Quote("Simplicity is prerequisite for reliability.", "Edsger W. Dijkstra"));
        quoteList.add(new Quote("First, solve the problem. Then, write the code.", "John Johnson"));
        quoteList.add(new Quote("Experience is simply the name we give our mistakes.", "Oscar Wilde"));
        quoteList.add(new Quote("In the middle of difficulty lies opportunity.", "Albert Einstein"));
        quoteList.add(new Quote("It always seems impossible until it's done.", "Nelson Mandela"));

        // Load an initial quote
        displayRandomQuote();

        // 1. Generate new quote on tap
        btnNewQuote.setOnClickListener(v -> displayRandomQuote());

        // 2. Share active quote via Android Share Sheet (WhatsApp, Messages, etc.)
        btnShareQuote.setOnClickListener(v -> {
            String quoteText = tvQuoteText.getText().toString();
            String quoteAuthor = tvQuoteAuthor.getText().toString();
            String shareMessage = quoteText + "\n" + quoteAuthor;

            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
            startActivity(Intent.createChooser(shareIntent, "Share Quote via"));
        });
    }

    private void displayRandomQuote() {
        int index = random.nextInt(quoteList.size());
        Quote selected = quoteList.get(index);
        tvQuoteText.setText("“" + selected.getText() + "”");
        tvQuoteAuthor.setText("— " + selected.getAuthor());
    }

    // Static Quote Model
    static class Quote {
        private final String text;
        private final String author;

        public Quote(String text, String author) {
            this.text = text;
            this.author = author;
        }

        public String getText() {
            return text;
        }

        public String getAuthor() {
            return author;
        }
    }
}
