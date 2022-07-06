package com.project1.main;

import java.io.File;

import com.project1.services.P1_RequestMapper;

import io.javalin.Javalin;
import io.javalin.plugin.metrics.MicrometerPlugin;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.binder.jvm.ClassLoaderMetrics;
import io.micrometer.core.instrument.binder.jvm.JvmGcMetrics;
import io.micrometer.core.instrument.binder.jvm.JvmMemoryMetrics;
import io.micrometer.core.instrument.binder.jvm.JvmThreadMetrics;
import io.micrometer.core.instrument.binder.system.DiskSpaceMetrics;
import io.micrometer.core.instrument.binder.system.ProcessorMetrics;
import io.micrometer.core.instrument.binder.system.UptimeMetrics;
import io.micrometer.prometheus.PrometheusConfig;
import io.micrometer.prometheus.PrometheusMeterRegistry;

public class maindriver {

	static int total = 0;

	public static void main(String[] args) {

		// Start a javalin serve
		// Over a network, we communicate between 2 machines using their OP (interne
		// protocol) address
		// When we have services running on our own machine, we use local host referring
		// to itself

		// we user a dependency in java called Micrometer:
		// We will build a Prometheus registry that will keep track of monitoring data
		// that can be stored by Prometheus

		// SimpleMeterRegistry sMR = new SimpleMeterRegistry();

		// In preperation for our prometheus database
		PrometheusMeterRegistry registry = new PrometheusMeterRegistry(PrometheusConfig.DEFAULT);

		registry.config().commonTags("applications", "My-monitored-app");

		new ClassLoaderMetrics().bindTo(registry);
		new JvmMemoryMetrics().bindTo(registry);
		new JvmGcMetrics().bindTo(registry);
		new JvmThreadMetrics().bindTo(registry);
		new UptimeMetrics().bindTo(registry);
		new ProcessorMetrics().bindTo(registry);
		new DiskSpaceMetrics(new File(System.getProperty("user.dir"))).bindTo(registry);

		// building a custom metric
		Counter counter = Counter.builder("path_request_to_logins").description("To keep track of logins")
				.tag("purpose", "counting").register(registry);

		Javalin app = Javalin.create(config -> {
			config.registerPlugin(new MicrometerPlugin(registry));
		}).start(8500);

		P1_RequestMapper requestMapper = new P1_RequestMapper();

		requestMapper.configureRoutes(app);

		app.get("/metrics", ctx -> {
			ctx.result(registry.scrape());
		});

		// custom metric -- figure out how to move this to request mapper
//		app.post("/login", ctx -> {
//			System.out.println("I want to keep track of how many people has logged in");
//			total++;
//			counter.increment(1);
//		});

		// Testing
//		AuthenticationDao user = new AuthenticationDaoImpl();
//		user.findEmployeeByUsername("Phamjust");
	}

}
