BEGIN{
	drop=0;
}
{
	if( $1 == "d")
	{
	drop++;
	}
}END{
  printf("Total number of %s Packets dropped due to congestion is = %d\n",$5,drop);
	}

	
