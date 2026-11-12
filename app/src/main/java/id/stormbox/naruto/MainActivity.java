package id.stormbox.naruto;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.net.Uri;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    private static final String GAMENATIVE_PACKAGE =
            "app.gamenative";

    private int dp(float value) {
        return (int) (value *
                getResources().getDisplayMetrics().density + 0.5f);
    }

    private TextView makeText(
            String value,
            int size,
            boolean bold
    ) {
        TextView text = new TextView(this);

        text.setText(value);
        text.setTextColor(Color.WHITE);
        text.setTextSize(size);

        if (bold) {
            text.setTypeface(
                    Typeface.DEFAULT,
                    Typeface.BOLD
            );
        }

        text.setPadding(
                0,
                dp(6),
                0,
                dp(6)
        );

        return text;
    }

    private void addSection(
            LinearLayout parent,
            String heading,
            String description
    ) {
        TextView title = makeText(
                heading,
                18,
                true
        );

        title.setTextColor(
                Color.rgb(255, 166, 60)
        );

        parent.addView(title);

        TextView body = makeText(
                description,
                14,
                false
        );

        parent.addView(body);

        View space = new View(this);

        space.setLayoutParams(
                new LinearLayout.LayoutParams(
                        1,
                        dp(12)
                )
        );

        parent.addView(space);
    }

    private Button makeButton(String label) {
        Button button = new Button(this);

        button.setText(label);
        button.setAllCaps(false);

        return button;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ScrollView scroll = new ScrollView(this);

        scroll.setBackgroundColor(
                Color.rgb(18, 20, 28)
        );

        LinearLayout root = new LinearLayout(this);

        root.setOrientation(
                LinearLayout.VERTICAL
        );

        root.setPadding(
                dp(24),
                dp(20),
                dp(24),
                dp(24)
        );

        scroll.addView(root);

        TextView heading = makeText(
                "STORMBOX",
                32,
                true
        );

        heading.setTextColor(
                Color.rgb(255, 166, 60)
        );

        root.addView(heading);

        TextView subtitle = makeText(
                "NARUTO EDITION",
                19,
                true
        );

        root.addView(subtitle);

        root.addView(makeText(
                "Windows Gaming Launcher for Android",
                14,
                false
        ));

        Button launchButton = makeButton(
                "BUKA GAMENATIVE"
        );

        root.addView(launchButton);

        launchButton.setOnClickListener(
                view -> openGameNative()
        );

        Button profileButton = makeButton(
                "SALIN PROFIL PERFORMA"
        );

        root.addView(profileButton);

        profileButton.setOnClickListener(
                view -> copyProfile()
        );

        addSection(
                root,
                "DEVICE PROFILE",
                "Device: Infinix Hot 50 4G\n"
                + "SoC: MediaTek Helio G100\n"
                + "GPU: Mali-G57 MC2\n"
                + "RAM: 8 GB\n"
                + "Android: 14"
        );

        addSection(
                root,
                "GAME PROFILE",
                "Naruto Shippuden: Ultimate Ninja Storm Revolution\n"
                + "Starting Resolution: 960 x 540\n"
                + "FPS Target: 30\n"
                + "Mode: Balanced"
        );

        addSection(
                root,
                "RUNTIME PROFILE",
                "Translation: Box64\n"
                + "Compatibility: Wine / Proton\n"
                + "Graphics: Pilih wrapper dan driver "
                + "yang tersedia di GameNative.\n"
                + "Runtime dikelola secara terpisah oleh GameNative."
        );

        addSection(
                root,
                "CARA MENGGUNAKAN",
                "1. Install GameNative.\n"
                + "2. Buka GameNative melalui tombol di atas.\n"
                + "3. Tambahkan game melalui fitur custom game "
                + "atau library.\n"
                + "4. Pilih file EXE game milikmu.\n"
                + "5. Pilih Box64 dan konfigurasi Wine/Proton "
                + "yang tersedia.\n"
                + "6. Terapkan profil performa secara manual."
        );

        addSection(
                root,
                "INFORMATION",
                "StormBox adalah launcher pendamping.\n"
                + "Aplikasi ini tidak membawa file game, "
                + "Box64, Wine, DXVK, atau driver grafis.\n"
                + "Pengaturan game tetap dijalankan "
                + "dari aplikasi GameNative."
        );

        setContentView(scroll);
    }

    private void openGameNative() {

        Intent intent =
                getPackageManager()
                .getLaunchIntentForPackage(
                        GAMENATIVE_PACKAGE
                );

        if (intent != null) {

            startActivity(intent);

        } else {

            Toast.makeText(
                    this,
                    "GameNative belum terpasang.",
                    Toast.LENGTH_LONG
            ).show();

            Intent browser = new Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(
                        "https://github.com/utkarshdalal/GameNative/releases"
                    )
            );

            startActivity(browser);
        }
    }

    private void copyProfile() {

        String profile =
                "STORMBOX NARUTO PROFILE\n"
                + "Device: Helio G100\n"
                + "GPU: Mali-G57 MC2\n"
                + "RAM: 8 GB\n"
                + "Resolution: 960x540\n"
                + "FPS Target: 30\n"
                + "Translation: Box64\n"
                + "Compatibility: Wine/Proton\n"
                + "Runtime: GameNative";

        ClipboardManager clipboard =
                (ClipboardManager)
                getSystemService(
                        Context.CLIPBOARD_SERVICE
                );

        clipboard.setPrimaryClip(
                ClipData.newPlainText(
                        "StormBox Profile",
                        profile
                )
        );

        Toast.makeText(
                this,
                "Profil berhasil disalin.",
                Toast.LENGTH_SHORT
        ).show();
    }
}
