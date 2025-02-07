import mysql.connector
import skfuzzy as fuzz
import numpy as np
import matplotlib.pyplot as plt


try:
    #Popunite parametre vasim podacima
    db = mysql.connector.connect(
        host="localhost",
        user="",
        password="",
        database="weather_db"
    )
    cursor = db.cursor()


    cursor.execute("SELECT id, weather_temperature FROM weather ORDER BY id DESC LIMIT 1")
    last_entry = cursor.fetchone()

    if last_entry:
        weather_id, temperature = last_entry
        print(f"Poslednja izmerena temperatura: {temperature}°C")


        temperatura = np.arange(-15, 40, 1)

        # Fuzzy skupovi
        niska_temperatura = fuzz.zmf(temperatura, -15, 5)
        srednja_temperatura = fuzz.trapmf(temperatura, [5, 10, 20, 25])
        visoka_temperatura = fuzz.smf(temperatura, 20, 35)


        mf_niska = fuzz.interp_membership(temperatura, niska_temperatura, temperature)
        mf_srednja = fuzz.interp_membership(temperatura, srednja_temperatura, temperature)
        mf_visoka = fuzz.interp_membership(temperatura, visoka_temperatura, temperature)


        if mf_niska > mf_srednja and mf_niska > mf_visoka:
            recommendation = "Topla jakna i kapa"
        elif mf_srednja > mf_niska and mf_srednja > mf_visoka:
            recommendation = "Lagani duks"
        else:
            recommendation = "Majica kratkih rukava"


        cursor.execute("UPDATE weather SET weather_outfit_recommendation = %s WHERE id = %s",
                       (recommendation, weather_id))
        db.commit()
        print(f"Preporuka '{recommendation}' upisana u bazu.")


        plt.figure()
        plt.xlabel("Temperatura (°C)")
        plt.ylabel("Funkcija pripadnosti")
        plt.plot(temperatura, niska_temperatura, 'r', label='Niska')
        plt.plot(temperatura, srednja_temperatura, 'b', label='Srednja')
        plt.plot(temperatura, visoka_temperatura, 'g', label='Visoka')
        plt.legend()
        plt.title('Fuzzy klasifikacija temperature')
        plt.show()

    else:
        print("Nema podataka u bazi.")

except mysql.connector.Error as err:
    print(f"Greška pri radu sa bazom: {err}")

finally:
    if db.is_connected():
        cursor.close()
        db.close()
