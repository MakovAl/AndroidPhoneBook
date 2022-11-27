package com.makoval.androidphonebook.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.makoval.androidphonebook.Formatter.UserList;
import com.makoval.androidphonebook.R;
import com.makoval.androidphonebook.UserPage.DeveloperPageActivity;
import com.makoval.androidphonebook.UserPage.ManagerPageActivity;
import com.makoval.androidphonebook.Users.Developer;
import com.makoval.androidphonebook.Users.Manager;
import com.makoval.androidphonebook.Users.User;


/**
 * Адаптер для отображения списка пользователей на экране
 */
public class PhoneBookAdapter extends ArrayAdapter<User> {

    /**
     * Контекст приложения
     */
    private Context context;
    /**
     * Список пользователей для отображения на экран
     */
    private UserList users;

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


        TextView name = (TextView) view.findViewById(R.id.name);
        TextView phone = (TextView) view.findViewById(R.id.phone);
        ImageView imageView = (ImageView) view.findViewById(R.id.avatar);
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

        return view;
    }

}
