= Keynote08Remote

Keynote08Remoteは、Keynoteをリモート制御するアプリケーションです。
Keynote08Remoteは、iOSのアプリケーションのKeynote RemoteがサポートしていないKeynote '08でも使用することができます。
コントロールはiOS(v4.2.1以降)のSafariから行ないます。

== ダウンロード

http://github.com/Kanasansoft/Keynote08Remote/downloads

== 実行

サーバ側で、Keynote08Remoteの実行ファイルをダブルクリックするか、ターミナルからコマンドを実行して下さい。

実行ファイル(x.x.xはバージョン番号です。)
* Windows用:Keynote08Remote-x.x.x.exe.
* Macintosh用:Keynote08Remote-x.x.x.app.

コマンド(x.x.xはバージョン番号です。)

% java -jar Keynote08Remote-x.x.x.jar 

Keynote08Remoteのアイコンが表示されていれば、実行中となります。

* Windows用:タスクトレイに表示
* Macintosh用:メニューバーに表示
* Linux用:メニューバーに表示

== 使い方

iOS(v4.2.1以降)のSafariから、"http:[server address]:40320/ios.html"にアクセスして下さい。

== 終了

Keynote08Remoteのiconを右クリックします。
Quitを選択して下さい。

== 必須条件

=== サーバ側

Java SE 6

=== クライアント側

WebSocketに対応したウェブブラウザ

== 注釈

現バージョンはポート番号の変更はできません。
