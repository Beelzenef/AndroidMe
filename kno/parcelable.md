# Parcelable

La [interfaz Parcelable](https://developer.android.com/reference/android/os/Parcelable.html) añade código a nuestras Clases POJO para transportarlas entre Activities. Las encapsula para ser recogidas en otra instancia en una nueva Activity, que la mostrará, operará con ella, cambiará sus datos...

Crearemos una Clase para ejemplificar:

```java
public class Post {

    private String title;
    private String link;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return title;
    }
}
```

El código a añadir que necesitaremos en gran parte ha de ser sobreescrito, pero algunos campos lo escribiremos de nuestra propia mano.

Un constructor privado que recibe un Parcel, a través del cual construir. Y un campo CREATOR, que es llamado para generar una instancia desde Parcelable.

```java
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(link);
    }

    private Post(Parcel source) {
        this.title = source.readString();
        this.link = source.readString();
    }

    public static Creator<Post> CREATOR = new Creator<Post>() {
        @Override
        public Post createFromParcel(Parcel parcel) {
            return new Post(parcel);
        }

        @Override
        public Post[] newArray(int i) {
            return new Post[0];
        }
    };
```

La ausencia de este código genera fallos a la hora de transportar instancias POJO entre Activities.

---
#### [Volver a inicio](../README.md)