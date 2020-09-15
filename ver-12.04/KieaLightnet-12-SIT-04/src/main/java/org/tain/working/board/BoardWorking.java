package org.tain.working.board;

import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tain.domain.board.Board;
import org.tain.repository.board.BoardRepository;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;
import org.tain.utils.JsonPrint;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class BoardWorking {

	@Autowired
	private BoardRepository boardRepository;
	
	public void loading() {
		log.info("KANG-20200808 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			IntStream.rangeClosed(1, 200).forEach(index -> {
				Board board = this.boardRepository.save(Board.builder()
						.title("제목-" + index)
						.subTitle("부제목-" + index)
						.content("안녕하세요.\n내용입니다.\n반갑습니다.")
						.userId("kiea")
						.build());
				JsonPrint.getInstance().printPrettyJson(board);
			});
		}
	}
}
