package fm.douban.service;

import fm.douban.model.Song;

import java.util.List;

public interface SongService {

    Song get(String songId);

    List<Song> list(String subjectId);

}