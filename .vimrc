set ruler
set cursorline
syntax on
colorscheme solarized
set number
set backspace=indent,eol,start
set ai
set si
set wrap
set confirm
set mouse=a
set lbr
set tw=500
set shiftwidth=4
set tabstop=4
inoremap {	{}<left>
inoremap {<CR>	{<CR>}<Esc>O
inoremap {{	{
inoremap {}	{}
inoremap (	()<left>
inoremap (<CR>	(<CR>)<Esc>O
inoremap ((	(
inoremap ()	()
inoremap [	[]<left>
inoremap [<CR>	[<CR>]<Esc>O
inoremap [[	[
inoremap []	[]
inoremap "	""<left>
inoremap ""	"
inoremap '	''<left>
inoremap ''	'
inoremap /*<CR>	/*<CR>*/<ESC>O

filetype plugin indent on
syntax enable

" Solarized Stuff
let g:solarized_termtrans = 1
set background=dark
