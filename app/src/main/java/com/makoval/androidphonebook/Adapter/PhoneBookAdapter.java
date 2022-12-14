package com.makoval.androidphonebook.Adapter;

import static androidx.core.content.FileProvider.getUriForFile;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.preference.PreferenceManager;

import com.makoval.androidphonebook.Formatter.UserList;
import com.makoval.androidphonebook.PhoneBookTools.PhoneBookTools;
import com.makoval.androidphonebook.R;
import com.makoval.androidphonebook.UserPage.DeveloperPageActivity;
import com.makoval.androidphonebook.UserPage.ManagerPageActivity;
import com.makoval.androidphonebook.Users.Developer;
import com.makoval.androidphonebook.Users.IOUser;
import com.makoval.androidphonebook.Users.Manager;
import com.makoval.androidphonebook.Users.User;

import java.io.File;
import java.io.IOException;


/**
 * Адаптер для отображения списка пользователей на экране
 */
public class PhoneBookAdapter extends ArrayAdapter<User> {

    /**
     * Контекст приложения
     */
    private final Context context;
    /**
     * Список пользователей для отображения на экран
     */
    private final UserList users;

    /**
     * @param context   Контекст приложения
     * @param usersList Список пользователей для отображения на экран
     */
    public PhoneBookAdapter(@NonNull Context context, UserList usersList) {
        super(context, R.layout.item, usersList);
        this.context = context;
        this.users = usersList;
    }

    /**
     * Заполняет список пользователей, данными из источника. В зависимости от типа пользователя
     * устанавливается соответсвующие изображение.
     * Дает возможность отмечать пользователей как важные
     * Дает возможность отправить контакт
     * Дает возможность добавить контакт в телефонную книгу
     * Дает возможность позвонить по номеру телефона из приложения
     *
     * @param position    Положение элемента в наборе данных адаптера элемента, представление которого мы хотим.
     * @param convertView Старое представление для повторного использования, если это возможно.
     *                    Примечание: перед использованием вы должны убедиться, что это представление
     *                    не является нулевым и имеет соответствующий тип. Если невозможно
     *                    преобразовать это представление для отображения правильных данных,
     *                    этот метод может создать новое представление.
     * @param parent      Родительский элемент, к которому в конечном итоге будет прикреплено
     *                    это представление
     * @return Представление, соответствующее данным в указанной позиции.
     */
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater =
                (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item, parent, false);

        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(context);
        SharedPreferences.Editor editSettings = sharedPreferences.edit();

        TextView name = view.findViewById(R.id.name);
        TextView phone = view.findViewById(R.id.phone);
        ImageView createContactImage = view.findViewById(R.id.add_contact);
        ImageView sendContactImage = view.findViewById(R.id.send_contact);
        ImageView imageView = view.findViewById(R.id.avatar);
        CheckBox checkBox = view.findViewById(R.id.checkBox);
        phone.setText(this.users.get(position).getUserPhoneNumber());


        if ((this.users.get(position) instanceof Developer)) {
            Developer developer = (Developer) this.users.get(position);
            String nameSurname = developer.getName() + " " + developer.getSurname();
            name.setText(nameSurname);
            imageView.setImageResource(R.drawable.developer);
        } else if ((this.users.get(position) instanceof Manager)) {
            Manager manager = (Manager) this.users.get(position);
            String nameSurname = manager.getName() + " " + manager.getSurname();
            name.setText(nameSurname);
            imageView.setImageResource(R.drawable.manager);
        }

        imageView.setOnClickListener(viewImage -> {
            Intent toUserPage = null;
            if (users.get(position) instanceof Developer) {
                toUserPage = new Intent(context, DeveloperPageActivity.class);
            } else if (users.get(position) instanceof Manager) {
                toUserPage = new Intent(context, ManagerPageActivity.class);
            }
            toUserPage.putExtra("@position", position);

            context.startActivity(toUserPage);

        });

        createContactImage.setOnClickListener(viewImage -> {
            PhoneBookTools pbt = new PhoneBookTools(context);

            if (!pbt.checkPhoneInList(phone.getText().toString())) {
                pbt.createNewContact(name.getText().toString(), phone.getText().toString());
                Toast.makeText(context, "Contact added", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Contact already exists in the phone book",
                        Toast.LENGTH_SHORT).show();
            }
        });

        sendContactImage.setOnClickListener(viewSendImage -> {
            String nameFile = IOUser.getFileSend();
            createFileToSend(position);

            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            shareIntent.putExtra(Intent.EXTRA_STREAM, getUriFileToSend(nameFile));
            shareIntent.setType("text/*");
            context.startActivity(Intent.createChooser(shareIntent, "Share File"));

        });

        phone.setOnClickListener(viewPhone -> {
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:" + phone.getText().toString()));
            context.startActivity(intent);
        });

        if (sharedPreferences.contains(phone.getText().toString())) {
            checkBox.setChecked(true);
        }

        checkBox.setOnClickListener(viewCheckBox -> {
            if (checkBox.isChecked()) {
                editSettings.putBoolean(phone.getText().toString(), false);
                editSettings.apply();
            } else {
                editSettings.remove(phone.getText().toString());
                editSettings.apply();
            }
        });

        return view;
    }


    private void createFileToSend(int position) {
        Intent writeIntent = new Intent();
        writeIntent.putExtra("@action", "writeShareContact");
        writeIntent.putExtra("@position", position);
        try {
            IOUser.getWriterSend().write(writeIntent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Uri getUriFileToSend(String nameFile) {
        File newFile = new File(context.getFilesDir(), nameFile);
        return getUriForFile(getContext(), "com.makoval.androidphonebook.provider", newFile);
    }
}
