package com.dicoding.picodiploma.rizkyalfikri_danappretechandroid_batch2_exam4.data.source.local.room

import android.os.AsyncTask
import com.dicoding.picodiploma.rizkyalfikri_danappretechandroid_batch2_exam4.data.source.local.entity.NewsEntity

class PopulateFirstData(db: NewsDatabase?) : AsyncTask<Unit, Unit, Unit>() {
    private val newsDao = db?.newsDao()
    override fun doInBackground(vararg p0: Unit?) {
        newsDao?.insertNews(
            NewsEntity(
                null,
                "Sports",
                "London - Arsene Wenger yang merupakan mantan manajer Arsenal pernah mengungkapkan kesalahan terbesarnya. Kesalahan itu adalah tidak membeli Gareth Bale.\n" +
                        "\n" +
                        "Dilansir dari Squawka, Arsene Wenger merupakan salah satu manajer Arsenal yang tersukses. Pria asal Prancis ini bertugas dari tahun 1996 sampai 2018, dengan torehan tiga gelar Premier League dan tujuh Piala FA.\n" +
                        "\n" +
                        "Wenger pun 'melahirkan' pemain-pemain hebat seperti Thierry Henry hingga Cesc Fabregas. Namun seperti manajer-manajer lainnya, kadang Wenger tak bisa mendapat pemain yang diinginkan, salah beli pemain, atau terlambat membeli pemain hingga disamber klub lain.\n",
                "https://akcdn.detik.net.id/community/media/visual/2020/04/22/2ce249a0-c2c2-4f52-acb0-02a4ac0774b7.jpeg?w=700&q=80",
                "Kesalahan Terbesar Wenger di Arsenal: Tidak Beli Gareth Bale"
            )
        )
        newsDao?.insertNews(
            NewsEntity(
                null,
                "Sports",
                "Jakarta - Arsenal saat ini sedang terseok-seok. Kehadiran Mikel Arteta diyakini akan mampu membawa The Gunners kembali bersaing di papan atas.\n" +
                        "Di klasemen Liga Inggris musim ini, Arsenal masih tertahan di posisi kesembilan. Mereka baru membukukan 40 poin, raihan angka Pierre-Emerick Aubameyang cs kurang dari separuh poin Liverpool di posisi pertama.\n" +
                        "\n" +
                        "Arsenal sudah tiga musim terakhir tak pernah menembus Liga Champions. Sebelumnya, tim Meriam London menjadi langganan kasta tertinggi kompetisi di Eropa sejak 2000 hinga 2016.\n" +
                        "\n" +
                        "Manuel Almunia menjadi bagian dari Arsenal saat masih reguler bermain d Liga Champions. Oleh karena itu, dia menyayangkan.",
                "https://akcdn.detik.net.id/community/media/visual/2020/03/27/0cc76037-9bcc-4f3f-b464-9ffd2e380ded_169.jpeg?w=700&q=80",
                "Mikel Arteta Bakal Bawa Arsenal Kembali Bersaing di Papan Atas"
            )
        )
        newsDao?.insertNews(
            NewsEntity(
                null,
                "Sports",
                "London - Arsenal disebut tidak akan meraih gelar juara apa pun saat ini, bahkan jika dilatih Pep Guardiola sekalipun. Lini belakang yang buruk menjadi alasannya.\n" +
                        "Arsenal memang tampil kurang mengesankan pada musim ini. Klub asal London Utara itu berada di urutan kesembilan klasemen sementara Liga Inggris dan hanya mampu menang 9 kali dari 28 laga yang sudah dimainkan.\n" +
                        "\n" +
                        "Tidak hanya itu, Arsenal juga harus tersingkir dari ajang-ajang lainnya semisal Liga Europa dan Piala Liga Inggris. Skuat asuhan Mikel Arteta itu kini hanya bisa berharap meraih gelar di Piala FA yang mana mereka telah melaju sampai perempat final.",
                "https://akcdn.detik.net.id/community/media/visual/2019/12/06/9af42e39-0596-453c-b5bc-43a9c57ad13b_169.jpeg?w=700&q=80",
                "Arsenal Saat Ini Bukanlah Tim Penantang Juara"
            )
        )
    }
}