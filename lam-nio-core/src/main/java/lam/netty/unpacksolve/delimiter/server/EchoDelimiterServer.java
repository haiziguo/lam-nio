package lam.netty.unpacksolve.delimiter.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

/**
* <p>
* delimiter server
* </p>
* @author linanmiao
* @date 2016年10月3日
* @versio 1.0
*/
public class EchoDelimiterServer {
	
	private static Logger logger = LoggerFactory.getLogger(EchoDelimiterServer.class);
	
	public void bind(int port) throws Exception{
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try{
			ServerBootstrap b = new ServerBootstrap();
			b.group(bossGroup, workerGroup)
			.channel(NioServerSocketChannel.class)
			.option(ChannelOption.SO_BACKLOG, 1024)
			.childOption(ChannelOption.SO_KEEPALIVE, true)
			.childHandler(new ChannelInitializer<SocketChannel>() {
				@Override
				protected void initChannel(SocketChannel socketChannel) throws Exception {
					ByteBuf delimiterByteBuf = Unpooled.copiedBuffer("$_".getBytes());
					ChannelPipeline p = socketChannel.pipeline();
					p.addLast(new DelimiterBasedFrameDecoder(1024, delimiterByteBuf));
					p.addLast(new StringDecoder());
					p.addLast(new EchoDelimiterServerHandler("$_"));
				}
			});
			
			ChannelFuture f = b.bind(port).sync();
			
			logger.info("listen at port:" + port);
			
			f.channel().closeFuture().sync();
		}finally{
			workerGroup.shutdownGracefully();
			bossGroup.shutdownGracefully();
		}
	}
	
	public static void main(String[] args) throws Exception{
		new EchoDelimiterServer().bind(8080);
	}

}
