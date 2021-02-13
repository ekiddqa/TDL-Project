package com.qa.tdl.service;

public class ToDoListService {

@Service
public class ToDoListService {

	private ToDoListRepo repo;

	private ModelMapper mapper;


	private ToDoListDto mapToDTO(ToDoList list) {
		return this.mapper.map(list, ToDoListDto.class);
	}

	@Autowired
	public ToDoListService(ToDoListRepo repo, ModelMapper mapper) {
		super();
		this.repo = repo;
		this.mapper = mapper;
	}


	public ToDoListDto create(ToDoList ToDoList) {
		return this.mapToDTO(this.repo.save(ToDoList));
	}


	public List<ToDoListDto> readllAll() {
		return this.repo.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
		
	}


	public ToDoListDto readById(Long id) {
		return this.mapToDTO(this.repo.findById(id).orElseThrow(ToDoListNotFoundException::new));
	}


	public ToDoListDto update(ToDoListDto ToDoListDto, Long id) {
		
		ToDoList toUpdate = this.repo.findById(id).orElseThrow(ToDoListNotFoundException::new);
		
		toUpdate.setName(ToDoListDto.getName());
			
		SpringBean.mergeNotNull(ToDoListDTO, toUpdate);
		return this.mapToDTO(this.repo.save(toUpdate));
		}


		public boolean delete(Long id) {
			this.repo.deleteById(id);
			return !this.repo.existsById(id);
		}
}
}
